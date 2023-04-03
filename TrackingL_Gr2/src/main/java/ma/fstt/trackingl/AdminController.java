package ma.fstt.trackingl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminController  {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField pfPassword;

    @FXML
    private TextField tfUsername;

    public boolean isValid() {
        boolean IsValid = true;
        if (!tfUsername.getText().equals(AdminApplication.USERNAME)) {
            IsValid = false;
        }

        if (!pfPassword.getText().equals(AdminApplication.PASSWORD)) {
            IsValid = false;
        }

        return IsValid;
    }

    public void onLogin() throws IOException {
        if (!isValid()) {
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load() );
        AdminApplication.currentStage.setScene(scene);
    }



}


