package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

/**
 * Created by Sankar Vijay on 27/02/2019.
 */
public class TrainLigneL extends AppCompatActivity {
    public static TextView horaires;
    public static TextView direction;
    public static TextView theure;
    public static TextView destination;
    public static TextView heureT;

    Button clickP;
    ImageView plans;
    ImageView exchange;
    Spinner gare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_horaires_ligne_l);

        horaires = (TextView) findViewById(R.id.horaire);
        direction = (TextView) findViewById(R.id.direction);
        heureT = (TextView) findViewById(R.id.heureT);
        destination = (TextView) findViewById(R.id.destination);
        this.plans = (ImageView) findViewById(R.id.plan2);
        this.exchange = (ImageView) findViewById(R.id.echange);
        this.gare = (Spinner) findViewById(R.id.gare);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListeTrain.class));
            }
        });

        clickP = (Button) findViewById(R.id.button2);
        clickP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchDataLigneL process = new FetchDataLigneL();
                process.execute();
            }
        });
        plans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TrainPlanLigneL.class);
                startActivity(myIntent);
            }
        });
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                theure = (TextView) findViewById(R.id.heure);
                                long date = System.currentTimeMillis();
                                DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG, Locale.FRANCE);
                                String dateString = df.format(date);
                                String[] dateS = dateString.split("G");
                                theure.setText(dateS[0]);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), GareFavoriteLigneL.class);
                Bundle extras = new Bundle();
                extras.putString("favori", gare.getSelectedItem().toString() + "");

                myIntent.putExtras(extras);
                startActivity(myIntent);
            }
        });

        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List listeGare = new ArrayList();
        listeGare.add("GARE ST LAZARE");
        listeGare.add("PONT CARDINET");
        listeGare.add("GARE DE CLICHY LEVALLOIS");
        listeGare.add("GARE D'ASNIERES");
        listeGare.add("GARE DE BECON LES BRUYERES");
        listeGare.add("GARE DES VALLEES");
        listeGare.add("GARE DE LA GARENNE COLOMBES");
        listeGare.add("GARE DE HOUILLES CARRIERES SUR SEINE");
        listeGare.add("Gare de Sartrouville");
        listeGare.add("GARE DE MAISONS LAFFITTE");
        listeGare.add("GARE D'ACHERES VILLE");
        listeGare.add("GARE DE CONFLANS FIN D'OISE");
        listeGare.add("GARE DE NEUVILLE UNIVERSITE");
        listeGare.add("Gare de Cergy Préfecture");
        listeGare.add("Gare de Cergy St Christophe");
        listeGare.add("Gare de Cergy le Haut");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listeGare);

        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        gare.setAdapter(adapter);


    }
}