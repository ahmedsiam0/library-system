module com.asu.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.asu.librarysystem to javafx.fxml;
    exports com.asu.librarysystem;
}