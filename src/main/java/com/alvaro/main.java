package com.alvaro;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Generador generador = new Generador();
            generador.generarPagina();
            System.out.println("Si va");
        } catch (Exception e) {
            System.out.println("No va");
        }
    }
}
