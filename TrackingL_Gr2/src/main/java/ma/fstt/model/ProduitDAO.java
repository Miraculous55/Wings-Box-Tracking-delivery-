package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

public class ProduitDAO extends BaseDAO<Produit>{
    public ProduitDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Produit object) throws SQLException {
        String request = "insert into produit (prix , description) values (? , ?)";
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getPrix());
        this.preparedStatement.setString(2 , object.getDescription());
        this.preparedStatement.execute();
    }

    @Override
    public void update(Produit object) throws SQLException {

    }

    @Override
    public  void delete(Produit object) throws SQLException {
        String request = "DELETE FROM produit WHERE id_produit = ?";
        System.out.println(object);
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setLong(1 , object.getId_produit());
        this.preparedStatement.execute();
    }

    @Override
    public List<Produit> getAll()  throws SQLException {

        List<Produit> mylist = new ArrayList<Produit>();
        String request = "select * from produit ";
        this.statement = this.connection.createStatement();
        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){
// mapping table objet
            mylist.add(new Produit(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3)));
        }
        return mylist;
    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        return null;
    }

    public void modify(Produit object) throws SQLException {
        String request = "update produit set prix = ?, description=? where id_produit =?" ;
        this.preparedStatement=this.connection.prepareStatement(request);
        this.preparedStatement.setFloat(1,parseFloat(object.getPrix()));
        this.preparedStatement.setString(2,object.getDescription());
        this.preparedStatement.setLong(3,object.getId_produit());

        this.preparedStatement.execute();
    }
}

