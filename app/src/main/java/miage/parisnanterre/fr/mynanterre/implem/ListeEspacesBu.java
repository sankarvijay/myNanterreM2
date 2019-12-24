package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;
import miage.parisnanterre.fr.mynanterre.R;

public class ListeEspacesBu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private List<String> espaces = new ArrayList<>();
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";



    private ListView m_listview;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_espaces_bu);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            conn = DriverManager.getConnection(url, user, psw);

            String sqliD = "SELECT * FROM bibliotheque;";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            while (rst.next()) {
                String name = rst.getString("nomSalle");
                espaces.add(name);
            }


            m_listview = (ListView) findViewById(R.id.list_view);

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, espaces);

            m_listview.setAdapter(adapter);
            m_listview.setOnItemClickListener(this);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Create intent
        id = (int) id + 1;
        Intent intent = new Intent(view.getContext(), FrequentationBu.class);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_MESSAGE, id + ""); //get id
        extras.putString(EXTRA_MESSAGE2, String.valueOf(this.m_listview.getItemAtPosition(position).toString())); //get nom espace bu
        intent.putExtras(extras);
        //Start details activity
        startActivity(intent);

    }




}
