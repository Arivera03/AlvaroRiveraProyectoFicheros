package com.alvaro;

public class Autor {
    private String nombre_autor;
    private int id_autor;
    private String genero_autor;
    private String fecha_nacimiento;
    private int edad;

    public Autor(String nombre_autor, int id_autor, String genero_autor, String fecha_nacimiento, int edad) {
        this.nombre_autor = nombre_autor;
        this.id_autor = id_autor;
        this.genero_autor = genero_autor;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getGenero_autor() {
        return genero_autor;
    }

    public void setGenero_autor(String genero_autor) {
        this.genero_autor = genero_autor;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
