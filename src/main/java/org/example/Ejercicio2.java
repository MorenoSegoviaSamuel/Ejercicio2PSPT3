package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Pedir al usuario la primera ruta (carpeta o fichero a comprimir)
            System.out.print("Por favor ingresa la primera ruta (carpeta o fichero a comprimir): ");
            String sourcePath = reader.readLine();

            // Pedir al usuario la segunda ruta (destino del archivo ZIP)
            System.out.print("Por favor ingresa la segunda ruta (destino del archivo ZIP): ");
            String destinationPath = reader.readLine();

            // Comprimir el archivo o carpeta
            compressToZip(sourcePath, destinationPath);

            System.out.println("La compresión ha sido completada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compressToZip(String sourcePath, String destinationPath) throws IOException {
        // Crear un flujo de salida para el archivo ZIP
        FileOutputStream fos = new FileOutputStream(destinationPath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        // Obtener el archivo o carpeta a comprimir
        Path source = Paths.get(sourcePath);

        // Comprimir el archivo o carpeta
        Files.walk(source)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    ZipEntry zipEntry = new ZipEntry(source.relativize(path).toString());
                    try {
                        zipOut.putNextEntry(zipEntry);
                        Files.copy(path, zipOut);
                        zipOut.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        // Cerrar el flujo de salida del archivo ZIP
        zipOut.close();
    }
}

