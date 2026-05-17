package ejercicios;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio1 {
    private File archivoActual;
    private String contenido;

    public void setContenido(String texto) {
        this.contenido = texto;
    }

    public String getContenido() {
        return this.contenido;
    }

    public File getArchivoActual() {
        return archivoActual;
    }

    public void setArchivoActual(File archivo) {
        this.archivoActual = archivo;
    }

    public void guardar() throws IOException {
        if (archivoActual != null) {
            FileWriter fw = new FileWriter(archivoActual);
            fw.write(contenido);
            fw.close();
        }
    }

    public void cargar(File archivo) throws IOException {
        this.archivoActual = archivo;
        FileReader fr = new FileReader(archivo);
        StringBuilder sb = new StringBuilder();
        int caracter;

        // Leemos caracter por caracter hasta que termine (-1)
        while ((caracter = fr.read()) != -1) {
            sb.append((char) caracter);
        }
        fr.close();
        this.contenido = sb.toString();
    }
}