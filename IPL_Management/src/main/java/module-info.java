module com.example.huhuhahahihi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.huhuhahahihi to javafx.fxml;
    exports com.example.huhuhahahihi;
}