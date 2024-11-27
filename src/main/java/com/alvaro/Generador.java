package com.alvaro;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.nio.file.*;
import java.util.*;
import org.json.*;


public class Generador  {
    private TemplateEngine templateEngine;

    public Generador() {
        // Configure Thymeleaf Template Engine
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public void generateSite() throws Exception {
        // Load config.ini and JSON data
        Properties config = loadConfig("config.ini");
        JSONObject data = new JSONObject(Files.readString(Path.of("data.json")));

        // Parse authors and books
        List<Map<String, Object>> authors = parseAuthors(data.getJSONArray("authors"));
        List<Map<String, Object>> books = parseBooks(data.getJSONArray("books"));

        // Generate index.html
        Context indexContext = new Context();
        indexContext.setVariable("siteName", config.getProperty("name"));
        indexContext.setVariable("siteTheme", config.getProperty("theme"));
        indexContext.setVariable("authors", authors);
        Files.writeString(Path.of("output/index.html"), templateEngine.process("index", indexContext));

        // Generate individual pages
        for (Map<String, Object> author : authors) {
            Context authorContext = new Context();
            authorContext.setVariable("author", author);
            authorContext.setVariable("books", filterBooksByAuthor(books, (Integer) author.get("id")));
            Files.writeString(Path.of("output/authors/" + author.get("id") + ".html"),
                    templateEngine.process("profile", authorContext));
        }
    }

    private Properties loadConfig(String path) throws Exception {
        Properties config = new Properties();
        config.load(Files.newBufferedReader(Path.of(path)));
        return config;
    }



    private List<Map<String, Object>> parseAuthors(JSONArray authorsArray) {
        List<Map<String, Object>> authors = new ArrayList<>();

        for (int i = 0; i < authorsArray.length(); i++) {
            JSONObject authorJson = authorsArray.getJSONObject(i);
            Map<String, Object> authorMap = new HashMap<>();

            authorMap.put("id_autor", authorJson.getInt("id_autor"));
            authorMap.put("nombre_autor", authorJson.getString("nombre_autor"));
            authorMap.put("genero_autor", authorJson.getString("genero_autor"));
            authorMap.put("fecha_nacimiento", authorJson.getString("fecha_nacimiento"));
            authorMap.put("edad", authorJson.getInt("edad"));

            authors.add(authorMap);
        }

        return authors;
    }

    private List<Map<String, Object>> parseBooks(JSONArray booksArray) {
        List<Map<String, Object>> books = new ArrayList<>();

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject bookJson = booksArray.getJSONObject(i);
            Map<String, Object> bookMap = new HashMap<>();

            bookMap.put("id_libro", bookJson.getInt("id_libro"));
            bookMap.put("nombre_libro", bookJson.getString("nombre_libro"));
            bookMap.put("fecha_publicacion", bookJson.getInt("fecha_publicacion"));
            bookMap.put("id_autor", bookJson.getInt("id_autor"));

            books.add(bookMap);
        }

        return books;
    }

    private List<Map<String, Object>> filterBooksByAuthor(List<Map<String, Object>> books, int authorId) {
        List<Map<String, Object>> filteredBooks = new ArrayList<>();

        for (Map<String, Object> book : books) {
            if ((int) book.get("id_autor") == authorId) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

}

