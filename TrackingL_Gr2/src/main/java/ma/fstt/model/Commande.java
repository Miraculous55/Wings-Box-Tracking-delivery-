package ma.fstt.model;

public class Commande {
        private Long id_commande ;
        private String nom ;
        private String distance ;
        public Commande() {
        }

        public Commande(Long id_commande, String nom, String distance) {
            this.id_commande = id_commande;
            this.nom = nom;
            this.distance = distance;
        }

        public Long getId_commande() {
            return id_commande;
        }
        public void setId_commande(Long id_commande) {
            this.id_commande = id_commande;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getDistance() {
            return distance;
        }
        public void setDistance(String distance) {
            this.distance = distance;
        }
        @Override
        public String toString() {
            return "Commande{" +
                    "id_commande=" + id_commande +
                    ", nom='" + nom + '\'' +
                    ", distance='" + distance + '\'' +
                    '}';
        }
}
