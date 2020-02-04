package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.TextKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

public class SandwichDispo extends AppCompatActivity {


    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private Spinner cafet;
    private Spinner produit;
    private String produitSelect;
    private EditText number;
    private Button confimer;


    private Integer oldpositionCafet;
    private Integer newpositionCafet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_sandwich);

         cafet = findViewById(R.id.spincafet);
         produit = findViewById(R.id.spinproduit);
         number = findViewById(R.id.tb_number);
         confimer = findViewById(R.id.btn_confirmerProduit);
        ImageView  Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ListeCrous.class);
                startActivity(myIntent);
            }
        });


        try
        {
            //connexion
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,psw);
        }

         catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        DisplayCafet();


        cafet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                oldpositionCafet =  cafet.getSelectedItemPosition();
                System.out.println("Je suis ici en premier " + oldpositionCafet);


                if(oldpositionCafet == 0)
                {
                    newpositionCafet = 1;
                }
                else if (oldpositionCafet == 1)
                {
                  newpositionCafet = 3;
                }
                else if(oldpositionCafet == 2)
                {
                    newpositionCafet = 4;
                }
                else if(oldpositionCafet == 3)
                {
                    newpositionCafet = 5;
                }

                System.out.println("ma nouvelle posisition ici1 est " + newpositionCafet);
                DisplayProduit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                oldpositionCafet =  cafet.getSelectedItemPosition();

                if(oldpositionCafet == 0)
                {
                    newpositionCafet = 1;
                }
                else if (oldpositionCafet == 1)
                {
                    newpositionCafet = 3;
                }
                else if(oldpositionCafet == 2)
                {
                    newpositionCafet = 4;
                }
                else
                {
                    newpositionCafet = 5;
                }


                System.out.println("ma nouvelle posisition ici2 est " + newpositionCafet);

                DisplayProduit();
            }








        });


        produit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                produitSelect =  produit.getSelectedItem().toString();
                System.out.println("Produit1 selectionner " + produitSelect);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                produitSelect =  produit.getSelectedItem().toString();
                System.out.println("Produit2 selectionner" + produitSelect);
            }


        });



        number.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Always use a TextKeyListener when clearing a TextView to prevent android
                    // warnings in the log
                    TextKeyListener.clear((number).getText());


                }
            }
        });




        //    confimer.setOnClickListener(handleClick);

        UpdateProduit();






    }


    public void DisplayCafet()

    {

        try
        {
            //recuperer uniquement les cafets sur la BDD
            String sqliD = "SELECT * FROM Crous WHERE id_bat IN (1,3,4,5) ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqliD);


            ArrayList<String> dataCafet = new ArrayList<String>();
            while (rs.next())
            {
                String id = rs.getString("batiment");
                dataCafet.add(id);
            }
            String[] array = dataCafet.toArray(new String[0]);
            ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dataCafet);
            cafet.setAdapter(NoCoreAdapter);


        }
        catch (Exception e )
        {
            System.out.println("JE suis ici " + e );
            Toast.makeText(getApplicationContext(), "Probleme de connexion a la BDD", Toast.LENGTH_SHORT).show();
        }


    }





    public void DisplayProduit()
    {


        try
        {
            //recuperer les produits
            String sqliD = "SELECT * FROM vente WHERE id_bat ='" + newpositionCafet + "';";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqliD);


            ArrayList<String> dataProduit = new ArrayList<String>();
            while (rs.next())
            {
                String id = rs.getString("produit");
                dataProduit.add(id);
            }
            String[] array = dataProduit.toArray(new String[0]);
            ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dataProduit);
            produit.setAdapter(NoCoreAdapter);


        }
        catch (Exception e )
        {
            System.out.println("Je suis ici 2 " + e );
            Toast.makeText(getApplicationContext(), "Probleme de connexion a la BDD (vente)", Toast.LENGTH_SHORT).show();
        }
    }




    public void UpdateProduit()
    {


        confimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if( number.getText().equals(""))
                {
                    String numberToString = number.getText().toString();
                    Toast.makeText(getApplicationContext(), "Veuillez saisir un nombre de produit valide", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    try
                    {
                           System.out.println("NB PRODUIT SELECT" + number.getText().toString());
                            String sqliD = "UPDATE vente SET produitquantite ='" + number.getText().toString() + "'  WHERE id_bat='" + newpositionCafet + "' and produit ='" + produitSelect +  "';";

                            System.out.println("REQUETE ICI " + sqliD);


                        PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                        preparedStatement.executeUpdate();




                        Toast.makeText(getApplicationContext(), "Merci pour votre contribution !", Toast.LENGTH_SHORT).show();

                        number.getText().clear();

                    }
                    catch (Exception e)
                    {
                        System.out.println("insertion ici");

                        Toast.makeText(getApplicationContext(), "Veuillez saisir un nombre de produit valide", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }



            }
        });




    }




























}
