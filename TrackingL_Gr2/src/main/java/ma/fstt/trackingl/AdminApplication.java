package ma.fstt.trackingl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class AdminApplication extends Application {

    public static final String USERNAME="admin";
    public static final String PASSWORD="123";
    public static Stage currentStage;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wings Box");
        stage.setScene(scene);
        stage.show();
        currentStage=stage;
    }

    public static void main(String[] args) {

        launch();

    }
}
