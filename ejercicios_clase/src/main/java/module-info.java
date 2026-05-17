module com.example.ejercicios_clase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicios_clase to javafx.fxml;
    exports ejercicios;
    opens ejercicios to javafx.fxml;
}