module com.typhaine.binomotroon {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires mysql.connector.j;


    opens com.typhaine.binomotroon to javafx.fxml;
    exports com.typhaine.binomotroon;
    exports com.typhaine.binomotroon.Controllers;
    exports com.typhaine.binomotroon.Controllers.Admin;
    exports com.typhaine.binomotroon.Controllers.Professor;
    exports com.typhaine.binomotroon.Controllers.Student;
    exports com.typhaine.binomotroon.Models;
    exports com.typhaine.binomotroon.Views;
    opens com.typhaine.binomotroon.Models to javafx.fxml;

}