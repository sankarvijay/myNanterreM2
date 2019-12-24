package miage.parisnanterre.fr.mynanterre.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import miage.parisnanterre.fr.mynanterre.implem.ListeCrous;

public class CrousFragment extends ListFragment {
    //lance l'activté avec categories des crous
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Create intent
        Intent intent = new Intent(view.getContext(), ListeCrous.class);
        //Start details activity
        startActivity(intent);
    }
}
