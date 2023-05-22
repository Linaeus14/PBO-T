module com.personal {

    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.pbo to javafx.fxml;

    exports com.pbo;
}
