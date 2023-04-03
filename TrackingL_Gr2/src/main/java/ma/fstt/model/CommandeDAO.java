package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {
    public CommandeDAO() throws SQLException {

        super();
    }

    @Override
    public  void save(Commande object) throws SQLException {
        String request = "INSERT INTO commande (nom , distance) VALUES (? , ?)";
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getDistance());
        this.preparedStatement.execute();
    }

    @Override
    public void update(Commande object) throws SQLException {

    }

    @Override
    public  void delete(Commande object) throws SQLException {


        String request = "DELETE FROM commande WHERE id_commande = ?";
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , ""+object.getId_commande());
        this.preparedStatement.execute();

    }

    @Override
    public List<Commande> getAll()  throws SQLException {

        List<Commande> mylist = new ArrayList<Commande>();

        String request = "select * from commande ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){
// mapping table objet
            mylist.add(new Commande(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3)));
        }
        return mylist;
    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }

    public void modify(Commande commande) throws SQLException {
        String request = "update commande set nom=? ,  distance=? where id_commande=?";
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , ""+commande.getNom());
        this.preparedStatement.setString(2 , ""+commande.getDistance());
        this.preparedStatement.setString(3 , ""+commande.getId_commande());
        this.preparedStatement.execute();
    }
}
