package com.alvaro;

public class Libro {
    private int id_libro;
    private String nombre_libro;
    private int fecha_publicacion;
    private int id_autor;

    public Libro(int id_libro, String nombre_libro, int fecha_publicacion, int id_autor) {
        this.id_libro = id_libro;
        this.nombre_libro = nombre_libro;
        this.fecha_publicacion = fecha_publicacion;
        this.id_autor = id_autor;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public int getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(int fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }
}
