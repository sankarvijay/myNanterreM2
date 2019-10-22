package miage.parisnanterre.fr.mynanterre.implem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.adapter.SportGridAdapter;
import miage.parisnanterre.fr.mynanterre.fragment.SeancesFragment;
import miage.parisnanterre.fr.mynanterre.fragment.SportFragment;


public class ListeSport extends AppCompatActivity {
    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private List<Sport> liste = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Bundle extras = getIntent().getExtras();
        String stringVariableName = extras.getString(SportFragment.EXTRA_MESSAGE);
        final int idCategorie = Integer.parseInt(stringVariableName);

        List<Sport> image_details = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new SportGridAdapter(this, image_details));

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Accueil.class));
            }
        });

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                String nom = ((Sport) o).getTexte();

                try {
                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD = "SELECT horaire FROM sports where sport='" + nom + "' order by categorie ASC;";
                    Statement st = conn.createStatement();
                    ResultSet rst = st.executeQuery(sqliD);

                    while (rst.next()) {
                        String horaire = rst.getString("horaire");

                        ArrayList<String> liste = new ArrayList<>();
                        liste.add(horaire);

                        StringBuilder builder = new StringBuilder();
                        for (String value : liste) {
                            builder.append(value);
                        }
                        String text = builder.toString();

                        customDialog("Horaires", text);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnRdv = (Button) findViewById(R.id.buttonRdv);
        btnRdv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Plannification.class);
                //on récupere l'id categorie pour l'utiliser dans l'activité plannification
                myIntent.putExtra("ID_CATEGORIE", idCategorie);
                startActivityForResult(myIntent, 0);
            }
        });

        Button btnSeance = (Button) findViewById(R.id.btnSeances);
        btnSeance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.myFrame, new SeancesFragment()).commit();
            }

        });

    }

    private List<Sport> getListData() {
        try {

            conn = DriverManager.getConnection(url, user, psw);
            Bundle extras = getIntent().getExtras();
            String stringVariableName = extras.getString(SportFragment.EXTRA_MESSAGE);
            int idCategorie = Integer.parseInt(stringVariableName);

            String sqliD = "SELECT * FROM sports where categorie ='" + idCategorie + "';";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            while (rst.next()) {
                String nomSport = rst.getString("sport");
                String image = rst.getString("image");
                String[] imgSansType = image.split(Pattern.quote("."));
                int resID = getResources().getIdentifier(imgSansType[0], "drawable", getPackageName());
                Sport sport = new Sport(resID, nomSport);
                liste.add(sport);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;

    }


    public void customDialog(String title, String message) {
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.horaires);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);
        builderSingle.show();
    }
}