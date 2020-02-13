package miage.parisnanterre.fr.mynanterre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.implem.Seance;

public class SeanceAdapter extends RecyclerView.Adapter<SeanceAdapter.MyViewHolder> {

    private List<Seance> listeSeances;
    private Context mcon;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView numero;
        private TextView heured;
        private TextView heuref;
        private TextView sport;
        private TextView lieu;
        private TextView dateRdv;
        private TextView getsport;
        private TextView inscrit;


        private MyViewHolder(View view) {
            super(view);
            this.numero = view.findViewById(R.id.numSeance2);
            this.heured = view.findViewById(R.id.heured);
            this.heuref = view.findViewById(R.id.heuref);
            this.sport = view.findViewById(R.id.sport);
            this.lieu = view.findViewById(R.id.lieu);
            this.dateRdv = view.findViewById(R.id.dateSeance);
            this.getsport = view.findViewById(R.id.getsport);
            this.inscrit = view.findViewById(R.id.inscrit);

        }
    }

    public SeanceAdapter(List<Seance> listeSeances, Context context) {
        this.listeSeances = listeSeances;
        this.mcon = context;
    }


    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne_seance, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        Button inscription = holder.itemView.findViewById(R.id.inscription);
        final Seance seance = listeSeances.get(position);

        holder.numero.setText("Seance n°" + String.valueOf(seance.getNumero()));
        holder.sport.setText(seance.getSport());
        holder.lieu.setText("Lieu : " + seance.getLieu());
        holder.heured.setText("Début : " + seance.getHeured().toString());
        holder.heuref.setText("Fin : " + seance.getHeuref().toString());
        holder.dateRdv.setText("Date : " + seance.getDateRdv());
        holder.getsport.setText(seance.getSport());
        holder.inscrit.setText(String.valueOf(seance.getNbInscrit()));

        try {
            final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
            final String user = "u749839367_vijay";
            final String psw = "9IDCqTm8Lig2";
            Connection conn;

            conn = DriverManager.getConnection(url, user, psw);
            String sqliD2 = "SELECT nbInscrit FROM plannification_sport where sport= '" + seance.getSport() + "' and numero=" + seance.getNumero() + "";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD2);
            rst.next();
            int countF = rst.getInt("nbInscrit");
            holder.inscrit.setText(String.valueOf(countF) + " inscrits pour ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
                    final String user = "u749839367_vijay";
                    final String psw = "9IDCqTm8Lig2";
                    Connection conn;

                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD3 = "SELECT nbInscrit FROM plannification_sport where sport= '" + seance.getSport() + "' and numero=" + seance.getNumero() + "";
                    Statement st = conn.createStatement();
                    ResultSet rst = st.executeQuery(sqliD3);
                    rst.next();
                    int countF = rst.getInt("nbInscrit");
                    countF++;

                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD = "update plannification_sport set nbInscrit= ? where sport= '" + seance.getSport() + "' and numero=" + seance.getNumero() + "";
                    PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                    preparedStatement.setInt(1, countF);
                    preparedStatement.executeUpdate();
                    holder.inscrit.setText(String.valueOf(countF) + " inscrits pour ");
                    Toast.makeText(mcon, "Inscription prise en compte!", Toast.LENGTH_SHORT).show();
                    inscription.setEnabled(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button desinscription = holder.itemView.findViewById(R.id.desinscription);

        desinscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
                    final String user = "u749839367_vijay";
                    final String psw = "9IDCqTm8Lig2";
                    Connection conn;

                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD3 = "SELECT nbInscrit FROM plannification_sport where sport= '" + seance.getSport() + "' and numero=" + seance.getNumero() + "";
                    Statement st = conn.createStatement();
                    ResultSet rst = st.executeQuery(sqliD3);
                    rst.next();
                    int countF = rst.getInt("nbInscrit");
                    countF--;

                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD = "update plannification_sport set nbInscrit= ? where sport= '" + seance.getSport() + "' and numero=" + seance.getNumero() + "";
                    PreparedStatement preparedStatement = conn.prepareStatement(sqliD);
                    preparedStatement.setInt(1, countF);
                    preparedStatement.executeUpdate();
                    holder.inscrit.setText(String.valueOf(countF) + " inscrits pour ");
                    Toast.makeText(mcon, "Désinscription prise en compte!", Toast.LENGTH_SHORT).show();
                    desinscription.setEnabled(false);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listeSeances.size();
    }
}
