package miage.parisnanterre.fr.mynanterre.implem;

import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.fragment.AccueilFragment;
import miage.parisnanterre.fr.mynanterre.fragment.CrousFragment;
import miage.parisnanterre.fr.mynanterre.fragment.PlanFragment;
import miage.parisnanterre.fr.mynanterre.fragment.SportFragment;
import miage.parisnanterre.fr.mynanterre.fragment.TrainFragment;

public class Accueil extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //display the logomynanterre during 5 seconds,
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                FragmentManager fragmentManager;
                BottomNavigationView bottomNavigation;

                //set the new Content of your activity
                Accueil.this.setContentView(R.layout.navbar);


                bottomNavigation = findViewById(R.id.navigationView);
                fragmentManager = getSupportFragmentManager();

                bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    Fragment fragment;

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                        switch (menuItem.getItemId()) {
                            case R.id.navigation_accueil:
                                fragment = new AccueilFragment();
                                break;
                            case R.id.navigation_sports:
                                fragment = new SportFragment();
                                break;
                            case R.id.navigation_crous:
                                fragment = new CrousFragment();
                                break;
                            case R.id.navigation_train:
                                fragment = new TrainFragment();
                                break;
                            case R.id.navigation_plan:
                                fragment=new PlanFragment();
                            default:
                                break;
                        }
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.container, fragment).commit();
                        return true;
                    }
                });
            }
        }.start();
    }
}

