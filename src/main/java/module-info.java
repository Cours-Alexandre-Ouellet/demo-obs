module edu.uqtr.demoobs {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.uqtr.demoobs to javafx.fxml;
    exports edu.uqtr.demoobs;
}