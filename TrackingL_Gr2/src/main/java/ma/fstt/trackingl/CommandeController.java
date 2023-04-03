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
import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    @FXML
    private TextField nom ;

    public long idCommandeSelectionne;
    @FXML
    private TextField distance ;
    @FXML
    private TableView<Commande> mytable ;
    @FXML
    private TableColumn<Commande ,Long> col_id ;
    @FXML
    private TableColumn <Commande ,String> col_nom ;
    @FXML
    private TableColumn <Commande ,String> col_distance ;
    @FXML
    private Button save;
    @FXML
    private Button modify;
    @FXML
    protected void onSaveButtonClick() {

        // access a la bdd

        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            Commande liv = new Commande(0l , nom.getText() , distance.getText());

            commandeDAO.save(liv);

            UpdateTable();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_commande"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Commande,String>("Nom"));
        col_distance.setCellValueFactory(new PropertyValueFactory<Commande,String>("Distance"));
        mytable.setItems(this.getDataCommandes());
        addButtonToTable("Modifier",0);
        addButtonToTable("Supprimer",1);

    }

    public static ObservableList<Commande> getDataCommandes(){

        CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for (Commande ettemp : commandeDAO.getAll())
                listfx.add(ettemp);

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
        TableColumn<Commande, Void> colBtn = new TableColumn(ButtonName);
        Button btn1 = new Button();
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {

                    private final Button btn = new Button(ButtonName);


                    {
                        btn.setId(""+btnId);
                        btn.setOnAction((ActionEvent event)  -> {
                            CommandeDAO commandeDAO = null;
                            try {
                                commandeDAO = new CommandeDAO();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            Commande commande = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + commande);
                            if (Objects.equals(btn.getId(), "1")){

                                try {
                                    commandeDAO.delete(commande);
                                    UpdateTable();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

                            }else if(Objects.equals(btn.getId(), "0")){
                                save.setDisable(true);
                                modify.setDisable(false);
                                idCommandeSelectionne=commande.getId_commande();


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
            CommandeDAO commandeDAO = new CommandeDAO();
            Commande liv = new Commande(idCommandeSelectionne , nom.getText() ,distance.getText());
            commandeDAO.modify(liv);
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