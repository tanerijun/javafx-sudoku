module com.tanerijun.javafxsudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tanerijun.javafxsudoku to javafx.fxml;
    exports com.tanerijun.javafxsudoku;
}