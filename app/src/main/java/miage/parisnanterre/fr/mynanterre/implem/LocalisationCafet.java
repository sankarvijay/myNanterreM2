package miage.parisnanterre.fr.mynanterre.implem;

import android.location.Location;
import android.widget.ImageView;

import miage.parisnanterre.fr.mynanterre.*;


public class LocalisationCafet {


    private double latitude;
    private double longitutde;
    private double distance;
    private String nom;



    public LocalisationCafet(double latitude, double longitutde,  String nom)
    {
        this.latitude = latitude;
        this.longitutde = longitutde;
        this.distance = 0.00;
        this.nom = nom;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitutde() {
        return longitutde;
    }

    public void setLongitutde(double longitutde) {
        this.longitutde = longitutde;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}