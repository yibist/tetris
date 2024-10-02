module com.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.tetris to javafx.fxml;
    exports com.tetris;
}