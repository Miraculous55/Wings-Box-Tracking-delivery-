package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {

// trait bloc try catch
        try {


            ProduitDAO produitDAO = new ProduitDAO();
            for (Produit elmnt :produitDAO.getAll()) {
                System.out.println(elmnt); // shftih ra recupera man DB yess rani chft
            }
          //  Livreur liv = new Livreur(0l , "liv3" , "200000000");

            //livreurDAO.save(liv);

            //Livreur liv2 = new Livreur(0l , "liv2" , "100000000");


           // livreurDAO.save(liv2);






        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
