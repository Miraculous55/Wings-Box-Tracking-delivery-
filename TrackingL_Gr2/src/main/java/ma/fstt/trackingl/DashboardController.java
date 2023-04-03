package ma.fstt.trackingl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Button btnCommande;

    @FXML
    private Button btnLivreur;

    @FXML
    private Button btnProduit;

    public void onClickLivreur() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("Livreur.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminApplication.currentStage.setScene(scene);
}

    public void onClickCommande(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("Commande.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminApplication.currentStage.setScene(scene);
    }

    public void onClickProduits (ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("Produit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminApplication.currentStage.setScene(scene);
    }

    public void toAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminApplication.currentStage.setScene(scene);
    }
}
