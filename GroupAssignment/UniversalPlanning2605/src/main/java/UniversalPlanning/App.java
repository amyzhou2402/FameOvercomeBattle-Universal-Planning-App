package UniversalPlanning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        
        loadDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("PlannerLoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Universal Planner");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadDatabase() {
        Database.createLoginTable();
    }

}
