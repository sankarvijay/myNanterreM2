package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

import androidx.appcompat.app.AppCompatActivity;
import miage.parisnanterre.fr.mynanterre.R;

public class PlanBatiments extends AppCompatActivity {

    ImageView retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batiments_map);

        PhotoView photoView = findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.planbatiments);

        retour = findViewById(R.id.back);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Accueil.class);
                startActivity(myIntent);
            }
        });
    }
}

