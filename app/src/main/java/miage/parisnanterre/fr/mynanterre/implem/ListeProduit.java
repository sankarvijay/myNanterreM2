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
import miage.parisnanterre.fr.mynanterre.adapter.ProduitGridAdapter;


public class ListeProduit extends AppCompatActivity {

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private List<Produit> liste = new ArrayList<>();
    int burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_produit);


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
                startActivity(new Intent(getApplicationContext(), ListeCrous.class));
            }
        });

        List<Produit> donnees = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ProduitGridAdapter(this, donnees));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Bundle extras = getIntent().getExtras();
                String stringVariableName = extras.getString(CrousGridAdapter.EXTRA_MESSAGE);
                int idBat = Integer.parseInt(stringVariableName);
                String produit = ((Produit) o).getNomProduit();
                //On instancie notre layout en tant que View
                LayoutInflater factory = LayoutInflater.from(ListeProduit.this);
                final View alertDialogView = factory.inflate(R.layout.dialog_box_dispo, null);
                AlertDialog.Builder alertDialogBuilder;
                alertDialogBuilder = new AlertDialog.Builder(ListeProduit.this);
                alertDialogBuilder.setView(alertDialogView);

                Button btn1 = (Button) alertDialogView.findViewById(R.id.buttonok);
                Button btn2 = (Button) alertDialogView.findViewById(R.id.buttonko);

                btn1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // btnAdd1 has been clicked
                        try {

                            Date currentTime = Calendar.getInstance().getTime();

                            SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                            String s = f.format(currentTime);


                            conn = DriverManager.getConnection(url, user, psw);
                            String sqliD = "UPDATE vente SET dispo = 1 WHERE produit='" + produit + "' AND id_bat=" + idBat + ";";
                            String sqliD2 = "UPDATE vente SET vote='" + s + "'  WHERE produit='" + produit + "' AND id_bat=" + idBat + ";";
                            PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                            PreparedStatement preparedStatement2 = conn.prepareStatement(sqliD2);
                            preparedStatement.executeUpdate();
                            preparedStatement2.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "c'est noté!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ListeProduit.this, ListeCrous.class));
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
                            String sqliD = "UPDATE vente SET dispo = 2 WHERE produit='" + produit + "' AND id_bat=" + idBat + ";";
                            String sqliD2 = "UPDATE vente SET vote='" + s + "'  WHERE produit='" + produit + "' AND id_bat=" + idBat + ";";

                            PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                            PreparedStatement preparedStatement2 = conn.prepareStatement(sqliD2);
                            preparedStatement.executeUpdate();
                            preparedStatement2.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "c'est noté!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ListeProduit.this, ListeCrous.class));

                    }
                });


                alertDialogBuilder.create().show();
            }
        });
    }


    private List<Produit> getListData() {

        try {
            conn = DriverManager.getConnection(url, user, psw);
            Bundle extras = getIntent().getExtras();
            String stringVariableName = extras.getString(CrousGridAdapter.EXTRA_MESSAGE);
            int idBat = Integer.parseInt(stringVariableName);

            String sqliD = "SELECT * FROM vente where id_bat ='" + idBat + "'ORDER BY dispo ASC;;";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            if (!rst.isBeforeFirst()) {
                TextView nothing = (TextView) findViewById(R.id.nothing);
                nothing.setText("Ce resto/cafet ne propose pas de produits à vendre ;)");
            } else {
                while (rst.next()) {
                    String produit = rst.getString("produit");
                    int dispo = rst.getInt("dispo");

                    String v = rst.getString("vote");
                    String v2 = "Dernière information : " + v;

                    Produit produits = new Produit(dispo, produit, v2);
                    liste.add(produits);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}