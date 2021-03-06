package miage.parisnanterre.fr.mynanterre.implem;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;


public class FrequentationBu extends AppCompatActivity {

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_frequentation_bu);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        List<BarEntry> barEntries = new ArrayList<>();

        //Insertion des données
        try {
            conn = DriverManager.getConnection(url, user, psw);
            Bundle extras = getIntent().getExtras();
            String stringVariableName = extras.getString(ListeEspacesBu.EXTRA_MESSAGE);//id espace bu
            String stringVariableName2 = extras.getString(ListeEspacesBu.EXTRA_MESSAGE2);//nom de l'espace

            int idEspace = Integer.parseInt(stringVariableName); //recup id espace cliqué
            String nomEspace = stringVariableName2; //recup nom espace concerné

            //Get la frequentation de l'espace bu cliqué
            String sqliD = "SELECT * FROM frequentation_bu where id_bu ='" + idEspace + "';";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);
            while (rst.next()) {
                int xValues = rst.getInt("heure");
                int yValues = rst.getInt("proportion");

                //ajout au graphique ses données
                barEntries.add(new BarEntry(xValues,yValues));
            }

            TextView txtview = findViewById(R.id.nomEspace);
            txtview.setText(nomEspace);

            barChart = findViewById(R.id.barchart);

            //Config
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true);
            barChart.setMaxVisibleValueCount(100);
            barChart.setPinchZoom(false);
            barChart.setVisibility(View.VISIBLE);
            barChart.setDrawGridBackground(true);
            barChart.animateY(3000);

            Description description = new Description();
            description.setText("heure (abscisse), proportion % (ordonnée)");
            barChart.setDescription(description);

            BarDataSet barDataSet =new BarDataSet(barEntries, "Affluence en %");

            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

            BarData barData = new BarData(barDataSet);
            barData.setBarWidth(1f);

            barChart.setData(barData);
            barChart.invalidate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}