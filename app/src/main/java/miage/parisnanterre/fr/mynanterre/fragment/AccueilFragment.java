package miage.parisnanterre.fr.mynanterre.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.implem.Cgu;
import miage.parisnanterre.fr.mynanterre.implem.LiveTweet;
import miage.parisnanterre.fr.mynanterre.implem.PlanBatiments;


/**
 * Created by Sankar Vijay on 18/01/2019.
 */
public class AccueilFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.accueil, container, false);


        Button mButton = (Button) v.findViewById(R.id.cgu);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create intent
                Intent intent = new Intent(v.getContext(), Cgu.class);
                //Start details activity
                startActivity(intent);
            }
        });

        Button TweetButton = v.findViewById(R.id.Tweet);
        TweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(v.getContext(), LiveTweet.class);
                Bundle extras = new Bundle();
                myIntent.putExtras(extras);
                v.getContext().startActivity(myIntent);
            }
        });

        ImageView planBat = (ImageView) v.findViewById(R.id.planBat);
        planBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), PlanBatiments.class);
                startActivity(myIntent);
            }
        });
        return v;
    }
}