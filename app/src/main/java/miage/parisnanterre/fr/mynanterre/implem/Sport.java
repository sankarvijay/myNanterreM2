package miage.parisnanterre.fr.mynanterre.implem;

/**
 * Created by Sankar Vijay on 26/01/2019.
 */
public class Sport {

    private int image;
    private String texte;

    Sport(int image, String texte) {
        this.image = image;
        this.texte = texte;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "image=" + image +
                ", texte='" + texte + '\'' +
                '}';
    }
}

