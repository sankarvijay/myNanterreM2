package miage.parisnanterre.fr.mynanterre.implem;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

/**
 * Created by Sankar Vijay on 09/02/2019.
 */
public class TrainRerA extends AppCompatActivity {
    public static TextView horaires;
    public static TextView direction;
    public static TextView theure;
    public static TextView code;
    public static TextView destination;
    public static TextView heureT;
    public static TextView info;
    public static TextView title;

    Button click;
    Button clickP;
    ImageView refresh;
    ImageView plans;
    ImageView exchange;
    Spinner gare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_horaires_rer_a);

        horaires = (TextView) findViewById(R.id.horaire);
        direction = (TextView) findViewById(R.id.direction);
        code = (TextView) findViewById(R.id.code);
        heureT = (TextView) findViewById(R.id.heureT);
        destination = (TextView) findViewById(R.id.destination);
        info = (TextView) findViewById(R.id.info_message);
        title = (TextView) findViewById(R.id.info_titre);
        this.refresh = (ImageView) findViewById(R.id.refresh);
        this.plans = (ImageView) findViewById(R.id.plan);
        this.exchange = (ImageView) findViewById(R.id.echange);
        this.gare = (Spinner) findViewById(R.id.gare);




        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListeTrain.class));
            }
        });

        click = (Button) findViewById(R.id.button);
        clickP = (Button) findViewById(R.id.button2);

        plans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TrainPlanRerA.class);
                startActivity(myIntent);
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchDataAllerRerA process = new FetchDataAllerRerA();
                process.execute();
            }
        });

        clickP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchDataRetourRerA process = new FetchDataRetourRerA();
                process.execute();
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

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchTrafficRerA process = new FetchTrafficRerA();
                process.execute();
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentTitle(title.getText()).setContentText(info.getText()).
                        setContentTitle(title.getText()).setSmallIcon(R.drawable.common_google_signin_btn_icon_dark).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
            }
        });

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), GareFavoriteRerA.class);
                Bundle extras = new Bundle();
                extras.putString("favori", gare.getSelectedItem().toString() + "");

                myIntent.putExtras(extras);
                startActivity(myIntent);


            }
        });


        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List listeGare = new ArrayList();
        listeGare.add("Saint-Germain-en-Laye");
        listeGare.add("Le Vesinet-Le Pecq");
        listeGare.add("Le Vesinet-Centre");
        listeGare.add("Chatou-Croissy");
        listeGare.add("Rueil-Malmaison");
        listeGare.add("Nanterre-Ville");
        listeGare.add("Cergy-Le-Haut");
        listeGare.add("Cergy-Saint-Christophe");
        listeGare.add("Cergy-Prefecture");
        listeGare.add("Neuville-Universite");
        listeGare.add("Conflans-Fin d'Oise");
        listeGare.add("Acheres-Ville");
        listeGare.add("Poissy");
        listeGare.add("Acheres Grand Cormier");
        listeGare.add("Maisons-Laffitte");
        listeGare.add("Sartrouville");
        listeGare.add("Houilles Carrieres-sur-Seine");
        listeGare.add("Nanterre-Prefecture");
        listeGare.add("La Defense (Grande Arche)");
        listeGare.add("Charles de Gaulle-Etoile");
        listeGare.add("Auber");
        listeGare.add("Chatelet-Les-Halles");
        listeGare.add("Gare de Lyon");
        listeGare.add("Nation");
        listeGare.add("Vincennes");
        listeGare.add("Val de Fontenay");
        listeGare.add("Neuilly-Plaisance");
        listeGare.add("Bry-sur-Marne");
        listeGare.add("Noisy-le-Grand (Mont d'Est)");
        listeGare.add("Noisy-Champs");
        listeGare.add("Noisiel");
        listeGare.add("Lognes");
        listeGare.add("Torcy");
        listeGare.add("Bussy-Saint-Georges");
        listeGare.add("Val d'Europe");
        listeGare.add("Marne-la-Vallee Chessy");
        listeGare.add("Fontenay-sous-Bois");
        listeGare.add("Nogent-sur-Marne");
        listeGare.add("Joinville-le-Pont");
        listeGare.add("Saint-Maur Creteil");
        listeGare.add("Le Parc de Saint-Maur");
        listeGare.add("Champigny");
        listeGare.add("La Varenne-Chennevieres");
        listeGare.add("Sucy Bonneuil");
        listeGare.add("Boissy-Saint-Leger");

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