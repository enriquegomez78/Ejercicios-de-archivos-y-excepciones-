package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ejercicio3 {
    private String[] columnas;
    private List<String[]> filas;

    public ejercicio3() {
        filas = new ArrayList<>();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public List<String[]> getFilas() {
        return filas;
    }

    public void leerCSV(File archivo) throws IOException {
        columnas = null;
        filas.clear();

        if (archivo != null) {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                try {
                    // Separar los valores por comas
                    String[] valores = linea.split(",");

                    if (esPrimeraLinea) {
                        this.columnas = valores;
                        esPrimeraLinea = false;
                    } else {
                        this.filas.add(valores);
                    }
                } catch (Exception e) {
                    // Si una línea en específico está rota o corrupta, se captura aquí y el programa sigue leyendo las demás
                    System.err.println("Error al procesar la línea: " + linea + " -> " + e.getMessage());
                }
            }
            br.close();
        }
    }
}
