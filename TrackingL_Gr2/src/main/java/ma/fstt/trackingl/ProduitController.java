package ma.fstt.trackingl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
public class ProduitController implements Initializable {

    @FXML
    private TextField prixField ;

    public long idproduitSelectionne;
    @FXML
    private TextField descriptionField ;
    @FXML
    private TableView<Produit> mytable ;
    @FXML
    private TableColumn<Produit,Long> col_id ;
    @FXML
    private TableColumn <Produit , String> col_prix;
    @FXML
    private TableColumn <Produit ,String> col_description ;
    @FXML
    private Button save;
    @FXML
    private Button modify;
    @FXML
    protected void onSaveButtonClick() {

        // access a la bdd
        try {
            ProduitDAO produitDAO = new ProduitDAO();

            Produit liv = new Produit(0l , prixField.getText() , descriptionField.getText());

            produitDAO.save(liv);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));

        mytable.setItems(this.getDataProduits());
        addButtonToTable("Modifier",0);
        addButtonToTable("Supprimer",1);

    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            listfx.addAll(produitDAO.getAll());


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
    private void addButtonToTable(String ButtonName,int btnId) {
        if(mytable.getColumns().size()==5){
            return;
        }
        TableColumn<Produit, Void> colBtn = new TableColumn(ButtonName);
        Button btn1 = new Button();
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button(ButtonName);

                    {
                        btn.setId(""+btnId);
                        btn.setOnAction((ActionEvent event)  -> {
                            ProduitDAO produitDAO = null;
                            try {
                                produitDAO = new ProduitDAO();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            Produit produit = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + produit);
                            if (Objects.equals(btn.getId(), "1")){

                                try {
                                    produitDAO.delete(produit);
                                    UpdateTable();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }else if(Objects.equals(btn.getId(), "0")){
                                save.setDisable(true);
                                modify.setDisable(false);
                                idproduitSelectionne=produit.getId_produit();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        mytable.getColumns().add(colBtn);

    }

    public void onModifyButtonClick(ActionEvent actionEvent) {
        try {
            ProduitDAO produitDAO = new ProduitDAO();
            Produit liv = new Produit(idproduitSelectionne , prixField.getText() , descriptionField.getText());
            produitDAO.modify(liv);
            save.setDisable(false);
            modify.setDisable(true);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toDashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminApplication.currentStage.setScene(scene);

    }
}