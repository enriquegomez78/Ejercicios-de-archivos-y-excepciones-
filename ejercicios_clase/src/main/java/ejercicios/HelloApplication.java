package ejercicios;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Proyecto Integrador - Ejercicios 1, 2 y 3");

        // El contenedor por pestañas para separar los ejercicios
        TabPane tabPane = new TabPane();

        // ==========================================================
        // EJERCICIO 1: EDITOR DE NOTAS
        // ==========================================================
        Tab tab1 = new Tab("1. Editor de Notas");
        ejercicio1 ej1 = new ejercicio1();

        TextArea textArea = new TextArea();
        Button btnAbrir1 = new Button("Abrir");
        Button btnGuardar1 = new Button("Guardar");

        btnAbrir1.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(primaryStage);
            if (f != null) {
                try {
                    ej1.cargar(f);
                    textArea.setText(ej1.getContenido());
                    // Aquí capturamos explícitamente IOException como pide la rúbrica
                } catch (IOException ex) {
                    System.out.println("Error al cargar nota: " + ex.getMessage());
                }
            }
        });

        btnGuardar1.setOnAction(e -> {
            try {
                ej1.setContenido(textArea.getText());
                if (ej1.getArchivoActual() == null) {
                    FileChooser fc = new FileChooser();
                    File f = fc.showSaveDialog(primaryStage);
                    if (f != null) {
                        ej1.setArchivoActual(f);
                        ej1.guardar();
                    }
                } else {
                    ej1.guardar();
                }
                // Aquí también capturamos IOException
            } catch (IOException ex) {
                System.out.println("Error al guardar nota: " + ex.getMessage());
            }
        });

        VBox layout1 = new VBox(10, new HBox(10, btnAbrir1, btnGuardar1), textArea);
        tab1.setContent(layout1);


        // ==========================================================
        // EJERCICIO 2: ESTADÍSTICAS DE TEXTO
        // ==========================================================
        Tab tab2 = new Tab("2. Estadísticas");
        ejercicio2 ej2 = new ejercicio2();

        Button btnCargar2 = new Button("Analizar archivo .txt");
        TableView<String[]> tablaStats = new TableView<>();

        // Columna Métrica (Líneas, Palabras, etc.)
        TableColumn<String[], String> colMetrica = new TableColumn<>("Métrica");
        colMetrica.setCellValueFactory(d -> new SimpleStringProperty(d.getValue()[0]));

        // Columna Valor (El resultado numérico)
        TableColumn<String[], String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(d -> new SimpleStringProperty(d.getValue()[1]));

        tablaStats.getColumns().addAll(colMetrica, colValor);

        btnCargar2.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(primaryStage);
            if (f != null) {
                try {
                    ej2.procesarArchivo(f);
                    // Actualizamos las filas de la tabla con los datos del ejercicio 2
                    tablaStats.getItems().setAll(
                            new String[]{"Líneas", String.valueOf(ej2.getLineas())},
                            new String[]{"Palabras", String.valueOf(ej2.getPalabras())},
                            new String[]{"Caracteres", String.valueOf(ej2.getCaracteres())}
                    );
                } catch (IOException ex) {
                    System.out.println("Error al procesar estadísticas: " + ex.getMessage());
                }
            }
        });

        VBox layout2 = new VBox(10, btnCargar2, tablaStats);
        tab2.setContent(layout2);


        // ==========================================================
        // EJERCICIO 3: VISUALIZADOR CSV (DINÁMICO)
        // ==========================================================
        Tab tab3 = new Tab("3. Visualizador CSV");
        ejercicio3 ej3 = new ejercicio3();

        Button btnCargar3 = new Button("Cargar archivo .csv");
        TableView<String[]> tablaCSV = new TableView<>();

        btnCargar3.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(primaryStage);
            if (f != null) {
                try {
                    ej3.leerCSV(f);

                    // Limpiamos columnas y datos viejos antes de cargar el nuevo CSV
                    tablaCSV.getColumns().clear();

                    String[] columnas = ej3.getColumnas();
                    if (columnas != null) {
                        // Creamos las columnas dinámicamente usando el arreglo de la primera fila
                        for (int i = 0; i < columnas.length; i++) {
                            final int index = i;
                            TableColumn<String[], String> col = new TableColumn<>(columnas[i]);

                            // Le decimos a la columna qué índice del arreglo de strings debe pintar
                            col.setCellValueFactory(d -> {
                                if (index < d.getValue().length) {
                                    return new SimpleStringProperty(d.getValue()[index]);
                                }
                                return new SimpleStringProperty("");
                            });
                            tablaCSV.getColumns().add(col);
                        }
                        // Cargamos todas las filas en la tabla de un solo golpe
                        tablaCSV.getItems().setAll(ej3.getFilas());
                    }
                } catch (IOException ex) {
                    System.out.println("Error al cargar el CSV: " + ex.getMessage());
                }
            }
        });

        VBox layout3 = new VBox(10, btnCargar3, tablaCSV);
        tab3.setContent(layout3);


        // ==========================================================
        // CONFIGURACIÓN DE LA ESCENA GENERAL
        // ==========================================================
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        Scene scene = new Scene(tabPane, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
