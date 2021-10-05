module UniversalPlanning {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires java.sql;
    requires java.base;
    
    exports UniversalPlanning;
    opens UniversalPlanning to javafx.fxml;
    
}