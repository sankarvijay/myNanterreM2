package miage.parisnanterre.fr.mynanterre.implem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.location.Location;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import java.util.LinkedList;
import java.util.List;

import miage.parisnanterre.fr.mynanterre.R;

public class LocalisationCrousMain extends AppCompatActivity {


    public Location current;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localisationcafetlist);
        recyclerView = findViewById(R.id.recyclerView);


        ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListeCrous.class));
            }
        });

        // layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // adapter
        RecyclerView.Adapter adapter = new LocalisationCrousAdapter(getListData());
        recyclerView.setAdapter(adapter);

    }


    //generate a list of Link
    public  List<LocalisationCafet> getListData()
    {
        LocalisationListener PositionUser = new LocalisationListener(this);
        System.out.println("je suis ici Latitude :  " + PositionUser.getLatitude() +"Je suis ici Longitude " +  PositionUser.getLongitude());

        Double LatitudeUser = PositionUser.getLatitude();
        Double LongitudeUser = PositionUser.getLongitude();


        List<LocalisationCafet> cafets = new LinkedList<LocalisationCafet>();

        LocalisationCafet cafetTerrasse = new LocalisationCafet();

        cafetTerrasse.setImage(R.drawable.laterrasse);
        cafetTerrasse.setNom("Brasserie La Terrasse");
        cafetTerrasse.setInfo("11h - 14h30 en semaine");
        String distanceTerrasse = cafetTerrasse.CalculDistance(48.9057091,2.21298630000001,LatitudeUser,LongitudeUser);
        cafetTerrasse.setDistance("Se trouve à " + distanceTerrasse + " mètres");
        cafetTerrasse.setPlat("Spécialités asiatiques, grillades, tartes et salades");

        cafets.add(cafetTerrasse);



        LocalisationCafet cafetBU = new LocalisationCafet();

        cafetBU.setImage(R.drawable.cafetbu);
        cafetBU.setNom("Cafétéria de la BU");
        cafetBU.setInfo("8h30 - 18h30 en semaine, 10h - 16h les samedis");
        String DistanceBU = cafetBU.CalculDistance(48.892423,2.21533099999999,LatitudeUser,LongitudeUser);
        cafetBU.setDistance("Se trouve à " + DistanceBU + " mètres");
        cafetBU.setPlat("Ciabatta chauds, sandwichs, viennoiserie, tartes, boissons chaudes");

        cafets.add(cafetBU);


        LocalisationCafet cafetSandwicherie = new LocalisationCafet();

        cafetSandwicherie.setImage(R.drawable.sandwicherie);
        cafetSandwicherie.setNom("La Sandwicherie");
        cafetSandwicherie.setInfo("8h30 - 18h en semaine // RDC du bâtiment F");
        String DistanceSandwich = cafetSandwicherie.CalculDistance(48.9047283,2.20883019999997,LatitudeUser,LongitudeUser);
        cafetSandwicherie.setDistance("Se trouve à " + DistanceSandwich + " mètres");
        cafetSandwicherie.setPlat("Sandwichs, paninis, viennoiserie, tartes, boissons chaudes, jus de fruits frais");

        cafets.add(cafetSandwicherie);


        LocalisationCafet cafetBrasserieNanterre = new LocalisationCafet();

        cafetBrasserieNanterre.setImage(R.drawable.brasserie_nanterre);
        cafetBrasserieNanterre.setNom("Brasserie de Nanterre");
        cafetBrasserieNanterre.setInfo("8h00 - 16h en semaine");
        String DistanceBrasserie = cafetBrasserieNanterre.CalculDistance(  48.9045044,  2.21605640000007,LatitudeUser,LongitudeUser);
        cafetBrasserieNanterre.setDistance("Se trouve à " + DistanceBrasserie + " mètres");
        cafetBrasserieNanterre.setPlat("Sandwichs, Viennoiseries");

        cafets.add(cafetBrasserieNanterre);



        LocalisationCafet cafetcroissanterie = new LocalisationCafet();

        cafetcroissanterie.setImage(R.drawable.croissanterie_bag);
        cafetcroissanterie.setNom("La Croissanterie");
        cafetcroissanterie.setInfo("8h30 - 15h en semaine // RDC du bâtiment G");
        String DistanceCroissanterie = cafetcroissanterie.CalculDistance( 48.9047283, 2.2088302,LatitudeUser,LongitudeUser);
        cafetcroissanterie.setDistance("Se trouve à " + DistanceCroissanterie + " mètres");
        cafetcroissanterie.setPlat("Wraps, sandwichs, paninis, viennoiserie, tartes, boissons chaudes, jus de fruits frais");

        cafets.add(cafetcroissanterie);

        LocalisationCafet cafetAsiatique = new LocalisationCafet();

        cafetAsiatique.setImage(R.drawable.asiatique);
        cafetAsiatique.setNom("Cafétéria l'Asiatique");
        cafetAsiatique.setInfo("8h - 15h en semaine // RDC du bâtiment F");
        String DistanceAsiatique = cafetAsiatique.CalculDistance(48.9026656,2.213463,LatitudeUser,LongitudeUser);
        cafetAsiatique.setDistance("Se trouve à "  + DistanceAsiatique + " mètres");
        cafetAsiatique.setPlat("Pizza, soupes de légumes, pasta box, kebabs, salades composées, jus pressés");


        return cafets;


    }

}