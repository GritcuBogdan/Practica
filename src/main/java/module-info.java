module org.example.practika {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.practika to javafx.fxml;
    opens org.example.practika.DepartamentHR.Controller;
    exports org.example.practika;
}