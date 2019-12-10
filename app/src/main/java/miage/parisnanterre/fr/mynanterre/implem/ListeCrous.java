package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.adapter.CrousGridAdapter;


public class ListeCrous extends AppCompatActivity {

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private List<Crous> liste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_batiments);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Accueil.class));
            }
        });

        Button menuBtn = (Button) findViewById(R.id.menuCrous);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CarteCrous.class));
            }
        });


        List<Crous> donnees = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CrousGridAdapter(this, donnees));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                String batiment = ((Crous) o).getBatiment();

                //On instancie notre layout en tant que View
                LayoutInflater factory = LayoutInflater.from(ListeCrous.this);
                final View alertDialogView = factory.inflate(R.layout.dialog_box_frequentation, null);

                AlertDialog.Builder alertDialogBuilder;
                alertDialogBuilder = new AlertDialog.Builder(ListeCrous.this);
                alertDialogBuilder.setView(alertDialogView);

                Button btn1 = (Button) alertDialogView.findViewById(R.id.buttonfaible);
                Button btn2 = (Button) alertDialogView.findViewById(R.id.buttonmoyenne);
                Button btn3 = (Button) alertDialogView.findViewById(R.id.buttonforte);

                btn1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // btnAdd1 has been clicked
                        try {
                            Date currentTime = Calendar.getInstance().getTime();

                            SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                            String s = f.format(currentTime);

                            conn = DriverManager.getConnection(url, user, psw);
                            String sqliD = "UPDATE Crous SET frequentation = 1 WHERE batiment='" + batiment + "';";
                            String sqliD2 = "UPDATE Crous SET vote='" + s + "' WHERE batiment='" + batiment + "';";

                            PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                            PreparedStatement preparedStatement2 = conn.prepareStatement(sqliD2);
                            preparedStatement.executeUpdate();
                            preparedStatement2.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "c'est noté!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ListeCrous.this, ListeCrous.class));
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // btnAdd2 has been clicked
                        try {
                            Date currentTime = Calendar.getInstance().getTime();

                            SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                            String s = f.format(currentTime);

                            conn = DriverManager.getConnection(url, user, psw);
                            String sqliD = "UPDATE Crous SET frequentation = 2 WHERE batiment='" + batiment + "';";
                            String sqliD2 = "UPDATE Crous SET vote='" + s + "' WHERE batiment='" + batiment + "';";

                            PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                            PreparedStatement preparedStatement2 = conn.prepareStatement(sqliD2);

                            preparedStatement.executeUpdate();
                            preparedStatement2.executeUpdate();


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "c'est noté!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ListeCrous.this, ListeCrous.class));

                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // btnAdd3 has been clicked
                        try {
                            Date currentTime = Calendar.getInstance().getTime();

                            SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                            String s = f.format(currentTime);

                            conn = DriverManager.getConnection(url, user, psw);
                            String sqliD = "UPDATE Crous SET frequentation = 3 WHERE batiment='" + batiment + "';";
                            String sqliD2 = "UPDATE Crous SET vote='" + s + "' WHERE batiment='" + batiment + "';";

                            PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                            PreparedStatement preparedStatement2 = conn.prepareStatement(sqliD2);

                            preparedStatement.executeUpdate();
                            preparedStatement2.executeUpdate();


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), "c'est noté!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(ListeCrous.this, ListeCrous.class));

                    }
                });

                alertDialogBuilder.create().show();
            }
        });

        FloatingActionButton MenuCrous = findViewById(R.id.MenuCrous);
        MenuCrous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(), "TEXT",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), MenuCrous.class);
                Bundle extras = new Bundle();
                myIntent.putExtras(extras);
                getApplicationContext().startActivity(myIntent);

            }
        });


        FloatingActionButton TweetButton = findViewById(R.id.Tweet);
        TweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), LiveTweet.class);
                Bundle extras = new Bundle();
                myIntent.putExtras(extras);
                getApplicationContext().startActivity(myIntent);
            }
        });


        FloatingActionButton Geo = findViewById(R.id.Geo);
        Geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), LocalisationCrous.class);
                Bundle extras = new Bundle();
                myIntent.putExtras(extras);
                getApplicationContext().startActivity(myIntent);

            }
        });

    }

    private List<Crous> getListData() {
        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat f = new SimpleDateFormat("HHmmss");
        String s = f.format(currentTime);
        int i = Integer.parseInt(s);

        try {
            conn = DriverManager.getConnection(url, user, psw);

            String sqliD = "SELECT * FROM Crous WHERE ouverture<=" + i + " AND fermeture>=" + i + " ORDER BY frequentation ASC;";
            System.out.println(sqliD);
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            if(!rst.isBeforeFirst()) {
                TextView nothing = (TextView) findViewById(R.id.nothing);
                nothing.setText("Il n'y a actuellement aucun restaurant/cafet ouvert ;)");
            }
            else {
                while (rst.next()) {
                    String batiment = rst.getString("batiment");
                    String lieu = rst.getString("lieu");
                    int frequentation = rst.getInt("frequentation");
                    int id = rst.getInt("id_bat");
                    String v = rst.getString("vote");
                    String v2 = "Dernière info : " + v;
                    Crous crous = new Crous(id, batiment, lieu, frequentation, v2);
                    liste.add(crous);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public void customDialog(String title, String message) {
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);
        builderSingle.show();
    }
}