module org.example.practika {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.example.practika to javafx.fxml;
    opens org.example.practika.DepartamentHR.Controller to javafx.fxml;
    opens org.example.practika.DepartamentHR.Entity;

    exports org.example.practika;
    exports org.example.practika.DepartamentHR.Controller;

}
