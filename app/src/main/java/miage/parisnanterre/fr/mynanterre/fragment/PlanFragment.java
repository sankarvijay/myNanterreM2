package miage.parisnanterre.fr.mynanterre.fragment;

import android.content.Intent;

import android.os.Bundle;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import miage.parisnanterre.fr.mynanterre.implem.PlanBatimentCarte;
import miage.parisnanterre.fr.mynanterre.implem.PlanGoogleMaps;

/**
 * Created by Sankar Vijay on 20/10/2019.
 */
public class PlanFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Create intent
        Intent intent = new Intent(view.getContext(), PlanGoogleMaps.class);
        //Start details activity
        System.out.println("je suis planfrag");

        startActivity(intent);
    }

}
