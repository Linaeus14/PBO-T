module com.pbo {

    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pbo to javafx.fxml;
    exports com.pbo;
}
