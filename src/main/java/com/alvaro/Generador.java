package com.alvaro;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.nio.file.*;
import java.util.*;
import org.json.*;

public class Generador {
    private TemplateEngine templateEngine;

    public Generador() {
        // Configuración del motor de plantillas
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix("/home/alvrivviv/IdeaProjects/AlvaroRiveraProyectoFicheros/src/main/resources/templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public void generarPagina() throws Exception {
        Properties config = new Properties();
        config.load(Files.newBufferedReader(Path.of("/home/alvrivviv/IdeaProjects/AlvaroRiveraProyectoFicheros/src/main/resources/json/config.ini")));
        JSONObject data = new JSONObject(Files.readString(Path.of("/home/alvrivviv/IdeaProjects/AlvaroRiveraProyectoFicheros/src/main/resources/json/libros.json")));

        List<Autor> listaAutores = parsearAutores(data.getJSONArray("autores"));
        List<Libro> listaLibros = parsearLibros(data.getJSONArray("libros"));

        Path outputPath = Path.of("src/main/resources/ficherosweb");
        Files.createDirectories(outputPath);

        Context indexContext = new Context();
        indexContext.setVariable("nombreWeb", config.getProperty("name"));
        indexContext.setVariable("temaWeb", config.getProperty("theme"));
        indexContext.setVariable("autores", listaAutores);
        Files.writeString(outputPath.resolve("index.html"), templateEngine.process("index", indexContext));
        Path autoresPath = outputPath.resolve("autores");
        Files.createDirectories(autoresPath);

        for (Autor autor : listaAutores) {
            Context contextoAutor = new Context();
            contextoAutor.setVariable("autor", autor);
            contextoAutor.setVariable("libros", filtrarLibrosPorAutor(listaLibros, autor.getId_autor()));
            Files.writeString(autoresPath.resolve("autor_nº" + autor.getId_autor() + ".html"),
                    templateEngine.process("perfiles", contextoAutor));
        }
    }


    private List<Autor> parsearAutores(JSONArray arrayAutores) {
        List<Autor> autores = new ArrayList<>();
        for (int i = 0; i < arrayAutores.length(); i++) {
            JSONObject autorJson = arrayAutores.getJSONObject(i);

            Autor autor = new Autor(
                    autorJson.getString("nombre_autor"),
                    autorJson.getInt("id_autor"),
                    autorJson.getString("genero_autor"),
                    autorJson.getString("fecha_nacimiento"),
                    autorJson.getInt("edad")
            );

            autores.add(autor);
        }
        return autores;
    }

    private List<Libro> parsearLibros(JSONArray arrayLibros) {
        List<Libro> libros = new ArrayList<>();
        for (int i = 0; i < arrayLibros.length(); i++) {
            JSONObject librosenJson = arrayLibros.getJSONObject(i);

            Libro libro = new Libro(
                    librosenJson.getInt("id_libro"),
                    librosenJson.getString("nombre_libro"),
                    librosenJson.getInt("fecha_publicacion"),
                    librosenJson.getInt("id_autor")
            );

            libros.add(libro);
        }
        return libros;
    }

    private List<Libro> filtrarLibrosPorAutor(List<Libro> listaLibros, int idAutor) {
        List<Libro> librosFiltrados = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.getId_autor() == idAutor) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }
}
