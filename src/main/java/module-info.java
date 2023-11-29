module com.asu.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;

    // hello
    opens com.asu.librarysystem to javafx.fxml;
    exports com.asu.librarysystem;
}
