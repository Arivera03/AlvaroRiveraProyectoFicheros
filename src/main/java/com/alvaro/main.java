package com.alvaro;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Generador generador = new Generador();

            generador.generateSite();

            System.out.println("¡Sitio web generado con éxito!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al generar el sitio web: " + e.getMessage());
        }
    }
}
