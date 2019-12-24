package miage.parisnanterre.fr.mynanterre.implem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.fragment.SportFragment;

public class FiltreDialog extends AppCompatDialogFragment {
private EditText dateChoice;
private Button btnGo;

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

                    conn = DriverManager.getConnection(url, user, psw);
                    String sqliD = "Select * from plannification_sport where categorie = 1 and dateRdv ='" + dateChoice.getText() + "';";

                    Statement st = conn.createStatement();
                    ResultSet rst = st.executeQuery(sqliD);
                    if(!rst.isBeforeFirst()) {
                        System.out.print("Pas de séance pour cette date");
                    }
                    else {
                        while (rst.next()) {

                            System.out.println("RESULT HERE : " + rst.getString("heured ") +
                                    rst.getString("heuref ") +
                                    rst.getString("sport ") +
                                    rst.getString("lieu ") +
                                    rst.getString("numero ") +
                                    rst.getString("dateRdv ") +
                                    rst.getString("categorie ") +
                                    rst.getString("nbInscrit ")
                            );

                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        dateChoice = view.findViewById(R.id.date);

        return builder.create();
    }
}
