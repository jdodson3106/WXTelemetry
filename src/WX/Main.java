package WX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("weather.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("WXTelemetry");
        primaryStage.setScene(new Scene(root, 1100, 900));
        primaryStage.show();

        // this populates the combobox.
        Controller cont = loader.getController();
        cont.populateChartBox();
        cont.populateChartData();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
