module com.example.javaassign {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaassign to javafx.fxml;
    exports com.example.javaassign;
}