package miage.parisnanterre.fr.mynanterre.implem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import miage.parisnanterre.fr.mynanterre.R;

public class FiltreDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener mDateSetListnener;

    private TextView mDisplayDate, mDisplayDate2;
    Calendar c;
    private static final String TAG = "Filtre";

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_box_filtre, null);

        mDisplayDate = view.findViewById(R.id.date);
        mDisplayDate2 = view.findViewById(R.id.date2);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int jour = c.get(Calendar.DAY_OF_MONTH);
                int mois = c.get(Calendar.MONTH);
                int annee = c.get(Calendar.YEAR);


                DatePickerDialog dialog = new DatePickerDialog(getContext(),
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

        builder.setView(view)
                .setTitle("Filtre par date")
                .setNegativeButton("retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                }).setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                try {
                   //on récupere l'id de la categorie du sport issu de ListeSport pour l'utliser dans la requete
                    Bundle bundle = getArguments();
                    int idCategorie = bundle.getInt("idcat_sport");

                    //Envoie des données vers sendFilterQueryToSeancesFragment() de ListeSport
                    ((ListeSport)getActivity()).sendFilterQueryToSeancesFragment(idCategorie,mDisplayDate2.getText());


                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD = "Select * from plannification_sport where categorie ='" + idCategorie + "' and dateRdv ='" + mDisplayDate2.getText() + "';";

                    Statement st = conn.createStatement();
                    ResultSet rst = st.executeQuery(sqliD);

                    if (rst.next() == false) {
                        Toast.makeText(getContext(), "Pas de séance pour cette date", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext(), "Séance(s) trouvée(s) ! " + rst.getString("heured") + " " +
                                rst.getString("heuref") + " " +
                                rst.getString("sport") + " " +
                                rst.getString("lieu") + " " +
                                rst.getString("numero") + " " +
                                rst.getString("dateRdv") + " " +
                                rst.getString("categorie") + " " +
                                rst.getString("nbInscrit")
                                , Toast.LENGTH_LONG).show();

                        while (rst.next()) {

                            System.out.println("RESULT HERE : " +
                                    rst.getString("heured") + " " +
                                    rst.getString("heuref") + " " +
                                    rst.getString("sport") + " " +
                                    rst.getString("lieu") + " " +
                                    rst.getString("numero") + " " +
                                    rst.getString("dateRdv") + " " +
                                    rst.getString("categorie") + " " +
                                    rst.getString("nbInscrit")
                            );

                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        });

        return builder.create();
    }
}
