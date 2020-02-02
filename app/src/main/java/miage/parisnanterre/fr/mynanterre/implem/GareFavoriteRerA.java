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

        horaires = findViewById(R.id.horaire2);
        direction = findViewById(R.id.direction2);
        direction2 = findViewById(R.id.direction3);
        code = findViewById(R.id.code2);
        heureT = findViewById(R.id.heureT2);
        destination = findViewById(R.id.destination2);
        info2 = findViewById(R.id.info_message2);
        title2 = findViewById(R.id.info_titre2);
        this.refresh = findViewById(R.id.refresh2);
        this.exchange = findViewById(R.id.echange2);
        this.click = findViewById(R.id.button2);

        ImageView back = findViewById(R.id.back);
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
                                theure = findViewById(R.id.heure2);
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
