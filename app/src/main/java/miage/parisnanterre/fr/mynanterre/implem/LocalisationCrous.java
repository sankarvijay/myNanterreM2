package miage.parisnanterre.fr.mynanterre.implem;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import miage.parisnanterre.fr.mynanterre.R;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class LocalisationCrous extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ListView listView ;
    ArrayList<LocalisationCafet> listeCafet = new ArrayList<LocalisationCafet>();
    private GoogleApiClient mGoogleApiClient;
    //  private Location currentLocation;
    private LocationManager locationManager;
    //private LocationRequest mLocationRequest; // comprendre comment LocationRequest fonctionne
    private FusedLocationProviderClient mFusedLocationClient;
    public LatLng latLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localisationcrous);


        listView = (ListView) findViewById(R.id.listView1);


        listeCafet.add (new LocalisationCafet(48.9045044,  2.21605640000007, "RU de Nanterre"));
        listeCafet.add (new LocalisationCafet(48.9057091, 2.21298630000001, "Brasserie La Terrasse"));
        listeCafet.add (new LocalisationCafet(48.892423, 2.21533099999999 ,"Cafétéria de la BU"));
        listeCafet.add (new LocalisationCafet(48.9047283,2.20883019999997, "Cafétéria La Sandwicherie" ));
        listeCafet.add (new LocalisationCafet (48.9045044,2.21605640000007 ,"Brasserie de Nanterre" ));
        listeCafet.add (new LocalisationCafet (48.9047283,2.2088302 ,"Cafétéria Bâtiment G" ));
        listeCafet.add (new LocalisationCafet (48.9045044,2.21605640000007,"Cafétéria le Millénium" ));
        listeCafet.add (new LocalisationCafet (48.9026656,2.213463,"Cafétéria l'Asiatique")) ;

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ContextCompat.checkSelfPermission(LocalisationCrous.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        }
        else {


        }

        Task<Location> l = mFusedLocationClient.getLastLocation();

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        }
                    }
                });



        ArrayList<String> values = new ArrayList<String>();
        int i = 0;
        Double distance = 0.0;
        while (i < listeCafet.size()){

            if(distance < 0)
                distance = distance * (-1);

            listeCafet.get(i).setDistance(distance);
            values.add(listeCafet.get(i).getNom());
            i++;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);



        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                int itemPosition     = position;


                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                double latBanc = listeCafet.get(position).getLatitude();
                double longBanc = listeCafet.get(position).getLongitutde();

                Toast.makeText(getApplicationContext(),
                        "La caféteria se trouve à "+ getDistanceMeters(latLng.latitude, latLng.longitude, latBanc, longBanc) + " metres " , Toast.LENGTH_LONG)
                        .show();





            }

        });
    }

    public static long getDistanceMeters(double lat1, double lng1, double lat2, double lng2) {

        double l1 = toRadians(lat1);
        double l2 = toRadians(lat2);
        double g1 = toRadians(lng1);
        double g2 = toRadians(lng2);

        double dist = acos(sin(l1) * sin(l2) + cos(l1) * cos(l2) * cos(g1 - g2));
        if(dist < 0) {
            dist = dist + Math.PI;
        }

        return Math.round(dist * 6378100);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}