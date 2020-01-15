package miage.parisnanterre.fr.mynanterre.implem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.fragment.SportFragment;

public class Plannification extends AppCompatActivity {
    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private EditText numero;
    private TextView heureD;
    private TextView heureF;
    Button planifier;
    private Spinner spinnerSport;
    private Spinner spinnerLieu;

    private List<String> sports = new ArrayList<>();
    private List<String> batiments = new ArrayList<>();

    private DatePickerDialog.OnDateSetListener mDateSetListnener;

    private TextView mDisplayDate, mDisplayDate2;
    private static final String TAG = "Plannification";
    Calendar c;

    int currentHour;
    int currentMinute;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formrdv);

        numero = (EditText) findViewById(R.id.numId);
        heureD = (TextView) findViewById(R.id.heureD);
        heureF = (TextView) findViewById(R.id.heureF);
        spinnerSport = (Spinner) findViewById(R.id.sport);

        spinnerLieu = (Spinner) findViewById(R.id.lieu);
        planifier = (Button) findViewById(R.id.planifier);

        mDisplayDate = (TextView) findViewById(R.id.date);
        mDisplayDate2 = (TextView) findViewById(R.id.date2);


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Accueil.class));
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int jour = c.get(Calendar.DAY_OF_MONTH);
                int mois = c.get(Calendar.MONTH);
                int annee = c.get(Calendar.YEAR);


                DatePickerDialog dialog = new DatePickerDialog(Plannification.this,
                        android.R.style.Theme_Material_DialogWhenLarge_NoActionBar,
                        mDateSetListnener,
                        annee, mois, jour);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
                dialog.show();
            }
        });
        mDateSetListnener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int annee, int mois, int jour) {
                mois = mois + 1;

                Log.d(TAG, "OndateSet: dd/mm/aaaa" + jour + "-" + mois + "-" + annee);

                if (jour >= 10 && mois >= 10) {
                    String date = jour + "-" + mois + "-" + annee;
                    mDisplayDate.setText(date);
                    String date2 = annee + "-" + mois + "-" + jour;
                    mDisplayDate2.setText(date2);
                } else if (jour < 10 && mois < 10) {
                    String date = "0" + jour + "-" + "0" + mois + "-" + annee;
                    String date2 = annee + "-" + "0" + mois + "-" + "0" + jour;
                    mDisplayDate.setText(date);
                    mDisplayDate2.setText(date2);
                } else if (jour >= 10 && mois < 10) {
                    String date = jour + "-" + "0" + mois + "-" + annee;
                    String date2 = annee + "-" + "0" + mois + "-" + jour;
                    mDisplayDate.setText(date);
                    mDisplayDate2.setText(date2);
                } else if (jour < 10 && mois >= 10) {
                    String date = "0" + jour + "-" + mois + "-" + annee;
                    String date2 = annee + "-" +  mois + "-" + "0" + jour;
                    mDisplayDate.setText(date);
                    mDisplayDate2.setText(date2);

                }
            }
        };

        heureD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                currentHour = c.get(Calendar.HOUR_OF_DAY);
                currentMinute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog2 = new TimePickerDialog(Plannification.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        heureD.setText(String.format("%02d:%02d:00", hourOfDay, minute));
                    }
                }, currentHour, currentMinute, true);

                dialog2.show();
            }
        });

        heureF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                currentHour = c.get(Calendar.HOUR_OF_DAY);
                currentMinute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog2 = new TimePickerDialog(Plannification.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        heureF.setText(String.format("%02d:%02d:00", hourOfDay, minute));
                    }
                }, currentHour, currentMinute, true);

                dialog2.show();
            }
        });

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //on récupere l'id de la categorie du sport issu de ListeSport pour l'utliser dans la requete
        Intent intent = getIntent();
        final Integer idCategorie = intent.getIntExtra("ID_CATEGORIE", 1);
        //on va recup la liste des sports en fonction de la catégorie choisie pour les placer dans le spinner des sports
        try {
            conn = DriverManager.getConnection(url, user, psw);


            String sqliD = "SELECT * FROM sports where categorie ='" + idCategorie + "';";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);
            while (rst.next()) {
                String nomSport = rst.getString("sport");
                sports.add(nomSport);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sports);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSport.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //on va récupérer la liste des batiments pour le lieu du rdv dans le spinner lieu
        try {
            conn = DriverManager.getConnection(url, user, psw);

            String sqlBatiment = "SELECT * FROM batiments;";
            Statement st2 = conn.createStatement();
            ResultSet rst2 = st2.executeQuery(sqlBatiment);

            while (rst2.next()) {
                String batiment = rst2.getString("nomBatiment");
                batiments.add(batiment);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, batiments);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLieu.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        planifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD = "insert into plannification_sport (numero,heured,heuref,dateRdv,sport,lieu,categorie) values (?,?,?,?,?,?,?) ;";
                    PreparedStatement preparedStatement = conn.prepareStatement(sqliD);

                    preparedStatement.setString(1, numero.getText().toString());
                    preparedStatement.setString(2, heureD.getText().toString());
                    preparedStatement.setString(3, heureF.getText().toString());
                    preparedStatement.setString(4, mDisplayDate2.getText().toString());
                    preparedStatement.setString(5, spinnerSport.getSelectedItem().toString());
                    preparedStatement.setString(6, spinnerLieu.getSelectedItem().toString());
                    preparedStatement.setInt(7, idCategorie);
                    preparedStatement.executeUpdate();
                    Toast.makeText(getApplicationContext(), "Votre séance a bien été planifié !", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), SportFragment.class));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}