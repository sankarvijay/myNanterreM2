package miage.parisnanterre.fr.mynanterre.implem;


import android.os.Bundle;

import java.sql.Time;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

public class Seance extends AppCompatActivity {
    String sport;
    String lieu;
    String dateRdv;
    Time heured;
    Time heuref;
    int numero;
    int nbInscrit;

    public Seance(int numero, Time heured, Time heuref, String sport, String lieu, String dateRdv,int nb) {
        this.numero = numero;
        this.heured = heured;
        this.heuref = heuref;
        this.sport = sport;
        this.lieu = lieu;
        this.dateRdv = dateRdv;
        this.nbInscrit=nb;

    }

    public int getNbInscrit() {
        return nbInscrit;
    }

    public void setNbInscrit(int nbInscrit) {
        this.nbInscrit = nbInscrit;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Time getHeured() {
        return heured;
    }

    public void setHeured(Time heured) {
        this.heured = heured;
    }

    public Time getHeuref() {
        return heuref;
    }

    public void setHeuref(Time heuref) {
        this.heuref = heuref;
    }

    public String getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(String dateRdv) {
        this.dateRdv = dateRdv;
    }
    

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_seances);
    }

    @Override
    public String toString() {
        return "Seance{" +
                "numero='" + numero + '\'' +
                ", sport='" + sport + '\'' +
                ", lieu='" + lieu + '\'' +
                ", heured=" + heured +
                ", heuref=" + heuref +
                ", dateRdv=" + dateRdv +
                '}';
    }
}
