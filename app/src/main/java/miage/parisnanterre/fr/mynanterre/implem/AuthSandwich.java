package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

public class AuthSandwich extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authsandwich);

        ImageView  Back = findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ListeCrous.class);
                startActivity(myIntent);
            }
        });

        EditText Password = findViewById(R.id.password);

        Button confimer = findViewById(R.id.btn_mdp);

        String mdp = "0000";




        confimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (Password.getText().toString().equals(mdp))
                {
                    Intent myIntent = new Intent(getApplicationContext(), SandwichDispo.class);
                    Bundle extras = new Bundle();
                    myIntent.putExtras(extras);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(myIntent);
                }
                else
                {
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;

                    Toast toast2 = Toast.makeText(context, "Mot de passe incorrect, veuillez réessayez" , duration);
                    toast2.show();
                }

            }
        });











    }





}