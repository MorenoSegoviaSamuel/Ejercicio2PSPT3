package org.example;

import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor ingresa la URL de la página web: ");
        String url = scanner.nextLine();

        CompletableFuture.supplyAsync(() -> descargarContenido(url))
                .thenAccept(contenido -> mostrarContenido(contenido))
                .join();
    }

    public static String descargarContenido(String url) {
        StringBuilder contenido = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new URL(url).openStream(), "UTF-8");
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }

    public static void mostrarContenido(String contenido) {
        System.out.println("Contenido descargado:\n" + contenido);
    }
}
