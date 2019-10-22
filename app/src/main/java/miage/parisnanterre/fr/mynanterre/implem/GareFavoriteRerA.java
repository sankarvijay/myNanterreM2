package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

/**
 * Created by Sankar Vijay on 23/02/2019.
 */
public class GareFavoriteRerA extends AppCompatActivity {
    public static TextView horaires;
    public static TextView direction;
    public static TextView direction2;
    public static TextView theure;
    public static TextView code;
    public static TextView destination;
    public static TextView heureT;
    public static TextView info2;
    public static TextView title2;
    ImageView refresh;
    ImageView exchange;
    String gareFavori = "";
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_horaires_favoris_rer_a);

        this.horaires = (TextView) findViewById(R.id.horaire2);
        this.direction = (TextView) findViewById(R.id.direction2);
        this.direction2 = (TextView) findViewById(R.id.direction3);
        this.code = (TextView) findViewById(R.id.code2);
        this.heureT = (TextView) findViewById(R.id.heureT2);
        this.destination = (TextView) findViewById(R.id.destination2);
        this.info2 = (TextView) findViewById(R.id.info_message2);
        this.title2 = (TextView) findViewById(R.id.info_titre2);
        this.refresh = (ImageView) findViewById(R.id.refresh2);
        this.exchange = (ImageView) findViewById(R.id.echange2);
        this.click = (Button) findViewById(R.id.button2);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TrainRerA.class));
            }
        });

        Bundle extras = getIntent().getExtras();
        gareFavori = extras.getString("favori");

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchDataFavRerA process = new FetchDataFavRerA(gareFavori);
                process.execute();
            }
        });

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TrainRerA.class);
                startActivity(myIntent);
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchTrafficFavRerA process = new FetchTrafficFavRerA();
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
                                theure = (TextView) findViewById(R.id.heure2);
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


    }
}
