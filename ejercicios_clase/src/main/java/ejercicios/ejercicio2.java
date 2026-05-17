package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ejercicio2 {
    private int lineas;
    private int palabras;
    private int caracteres;

    public int getLineas() {
        return lineas;
    }

    public int getPalabras() {
        return palabras;
    }

    public int getCaracteres() {
        return caracteres;
    }

    public void reset() {
        lineas = 0;
        palabras = 0;
        caracteres = 0;
    }

    public void procesarArchivo(File archivo) throws IOException {
        reset();

        if (archivo != null) {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            // Leemos línea por línea hasta que no haya más (null)
            while ((linea = br.readLine()) != null) {
                lineas++; // Sumamos una línea
                caracteres += linea.length(); // Sumamos la cantidad de caracteres de esa línea

                // Para contar palabras, quitamos espacios a los lados y separamos por los espacios internos
                String[] palabrasEnLinea = linea.trim().split("\\s+");

                // Si la línea no estaba vacía, sumamos la cantidad de palabras encontradas
                if (palabrasEnLinea.length > 0 && !palabrasEnLinea[0].isEmpty()) {
                    palabras += palabrasEnLinea.length;
                }
            }
            br.close();
        }
    }
}
