package miage.parisnanterre.fr.mynanterre.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.adapter.SeanceAdapter;
import miage.parisnanterre.fr.mynanterre.implem.Seance;

public class SeancesFragment extends Fragment {

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;
    private List<Seance> liste = new ArrayList<>();
    private SeanceAdapter sAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.liste_seances, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

        sAdapter = new SeanceAdapter(liste, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sAdapter);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        prepareSeanceData();

        }

    private void prepareSeanceData() {
        try {
            //on recupere l'idcategorie du sport selectionné pour trouver les séances associées
            Bundle extras = getActivity().getIntent().getExtras();
            String stringVariableName = extras.getString(SportFragment.EXTRA_MESSAGE);
            final int idCategorie = Integer.parseInt(stringVariableName);

            conn = DriverManager.getConnection(url, user, psw);
            String sqliD = "SELECT * FROM plannification_sport where categorie=" + idCategorie + " ORDER by dateRdv ASC";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            while (rst.next()) {

                int numero = rst.getInt("numero");
                Time heured = rst.getTime("heured");
                Time heuref = rst.getTime("heuref");
                String dateRdv = rst.getString("dateRdv");
                String sport = rst.getString("sport");
                String lieu = rst.getString("lieu");
                int nbInscrit = rst.getInt("nbInscrit");
                Seance seance = new Seance(numero, heured, heuref, sport, lieu, dateRdv, nbInscrit);
                liste.add(seance);

            }
            sAdapter.notifyDataSetChanged();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sAdapter.notifyDataSetChanged();
    }


    //Appelée par ListeSport sendFilterQueryToSeancesFragment
    public  void queryWithDateFiltered (int idCategorie, CharSequence dateChosen) {//pb ici genre je soit instacier un truc peut etre les methodes d'avant

        try {
            conn = DriverManager.getConnection(url, user, psw);
            String sqliD = "Select * from plannification_sport where categorie ='" + idCategorie + "' and dateRdv ='" + dateChosen + "';";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            while (rst.next()) {

                int numero = rst.getInt("numero");
                Time heured = rst.getTime("heured");
                Time heuref = rst.getTime("heuref");
                String dateRdv = rst.getString("dateRdv");
                String sport = rst.getString("sport");
                String lieu = rst.getString("lieu");
                int nbInscrit = rst.getInt("nbInscrit");
                Seance seance = new Seance(numero, heured, heuref, sport, lieu, dateRdv, nbInscrit);
                liste.add(seance);

            }
            sAdapter.notifyDataSetChanged();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sAdapter.notifyDataSetChanged();
    }
}
