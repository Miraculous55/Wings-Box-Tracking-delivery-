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
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;

import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField nom ;

    public long idlivreurSelectionne;
    @FXML
    private TextField tele ;
    @FXML
    private TableView<Livreur> mytable ;
    @FXML
    private TableColumn<Livreur ,Long> col_id ;
    @FXML
    private TableColumn <Livreur ,String> col_nom ;
    @FXML
    private TableColumn <Livreur ,String> col_tele ;
    @FXML
    private Button save;
    @FXML
    private Button modify;
    @FXML
    protected void onSaveButtonClick() {

        // access a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            Livreur liv = new Livreur(0l , nom.getText() , tele.getText());

            livreurDAO.save(liv);


            UpdateTable();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        }


    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur,String>("Nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur,String>("Telephone"));
        mytable.setItems(this.getDataLivreurs());
        addButtonToTable("Modifier",0);
        addButtonToTable("Supprimer",1);

    }

    public static ObservableList<Livreur> getDataLivreurs(){

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
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
        TableColumn<Livreur, Void> colBtn = new TableColumn(ButtonName);
        Button btn1 = new Button();
        Callback<TableColumn<Livreur, Void>, TableCell<Livreur, Void>> cellFactory = new Callback<TableColumn<Livreur, Void>, TableCell<Livreur, Void>>() {
            @Override
            public TableCell<Livreur, Void> call(final TableColumn<Livreur, Void> param) {
                final TableCell<Livreur, Void> cell = new TableCell<Livreur, Void>() {

                    private final Button btn = new Button(ButtonName);


                    {
                        btn.setId(""+btnId);
                        btn.setOnAction((ActionEvent event)  -> {
                            LivreurDAO livreurDAO = null;
                            try {
                                livreurDAO = new LivreurDAO();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            Livreur livreur = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + livreur);
                                if (Objects.equals(btn.getId(), "1")){

                                    try {
                                        livreurDAO.delete(livreur);
                                        UpdateTable();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }

                                }else if(Objects.equals(btn.getId(), "0")){
                                        save.setDisable(true);
                                        modify.setDisable(false);
                                    idlivreurSelectionne=livreur.getId_livreur();


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
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(idlivreurSelectionne , nom.getText() , tele.getText());
            livreurDAO.modify(liv);
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

