package miage.parisnanterre.fr.mynanterre.implem;

/**
 * Created by Sankar Vijay on 28/02/2019.
 */
public class Produit {
    private int dispo;
    private String nomProduit;
    private String vote;

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    Produit(int dispo, String nomProduit,String vote) {
        this.dispo = dispo;
        this.nomProduit = nomProduit;
        this.vote=vote;
    }

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    @Override
    public String toString() {
        return "Produit : " + nomProduit +
                ", disponibilite : " + dispo;
    }
}
