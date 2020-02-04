package miage.parisnanterre.fr.mynanterre.implem;

/**
 * Created by Sankar Vijay on 30/10/2019.
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.adapter.CustomWindowAdapter;


public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private GoogleMap mMap;
    Button displayData;

    public static String campus;
    public static String lettre;
    public static String nomUsage;
    public static String code;
    public static String descActivite;
    public static String coordonnesGps;
    public static String annee;
    public static String activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        FetchDataBatimentUniv myAsyncTasks = new FetchDataBatimentUniv();
        myAsyncTasks.execute();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                mMap.setBuildingsEnabled(false);

                LatLng sucre = new LatLng(48.903104, 2.215515);

                CameraPosition camera = new CameraPosition.Builder()
                        .target(sucre)
                        .zoom(18)  //limite ->21
                        .bearing(90) // 0 - 365
                        .tilt(35) // limite ->90
                        .build();
                mMap.setInfoWindowAdapter(new CustomWindowAdapter(GoogleMaps.this));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.903732,2.212187)).title("Batiment A\n").snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Réné Rémond"+"\n\n"+"Annéee de construction : 1964"+
                        "\n\n"+"Activités : Services administratifs (scolarité, inscriptions, bourses …) et fonctions support de certains laboratoires de recherche."));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.904943, 2.213485)).title("Batiment S\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Allice Milliat\n\n"+"Annéee de construction : 2006"+
                                "\n\n"+"Activités : UFR STAPS – Sciences et Techniques des Activités Physiques et Sportives.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.90296, 2.209572)).title("Batiment T\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Ephémère 3\n\n"+"Annéee de construction : 1994"+
                                "\n\n"+"Activités : Bâtiment précaire modulaire destiné à la démolition à court terme, accueillant quelques activités d’enseignement et des activités associatives.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902847, 2.211696)).title("Batiment C\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Bianka et René Zazzo\n\n"+"Annéee de construction : 1966"+
                                "\n\n"+"Activités : UFR SPSE – Sciences Psychologiques et Sciences de l’Education.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.90412, 2.211455)).title("Batiment W\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Max Weber\n\n"+"Annéee de construction : 2016"+
                                "\n\n"+"Activités : Bâtiment de la recherche en Sciences Humaines et Sociales.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.903104, 2.215515)).title("Batiment G\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Maurice Allais\n\n"+"Annéee de construction : 1968"+
                                "\n\n"+"Activités : UFR SEGMI – Sciences Economiques Gestion Mathématique Informatique.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.905188, 2.214416)).title("Batiment N\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Ephémère 2\n\n"+"Annéee de construction : 2001"+
                                "\n\n"+"Activités : SUFOM Service Universitaire de la Formation des Maitres.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.90223, 2.210858)).title("Batiment Chaufferie\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Chaufferie\n\n"+"Annéee de construction : 1966"+
                                "\n\n"+"Activités : Bâtiment technique abritant la chaufferie principale de l’Université alimentant un certain nombre de bâtiments depuis le réseau de chaleur (bât A, B, C, D, E, F, G, I, BU, CSU, Max Weber). Chaufferie de 12 MW composée de 4 chaudières.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902392, 2.212274)).title("Batiment DD\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Jean Rouch\n\n"+"Annéee de construction : 1992"+
                                "\n\n"+"Activités : UFR SSA – Sciences Sociales et Administration.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.90448, 2.212187)).title("Batiment V\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Ida Maier\n\n"+"Annéee de construction : 2010"+
                                "\n\n"+"Activités : UFR LCE – Langues et Cultures Etrangères.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.90226, 2.214115)).title("Batiment F\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Simone Veil\n\n"+"Annéee de construction : 1968"+
                                "\n\n"+"Activités : UFR DSP – Droit et Sciences Politiques.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.901941, 2.212835)).title("Batiment E\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Cémence Ramnoux\n\n"+"Annéee de construction : 1966"+
                                "\n\n"+"Activités : Service médical, SCUIO-IP (Service Commun Universitaire d’Information, d’Orientation et d’Insertion Professionnelle), COMETE (Centre Optimisé de MEdiatisation et de Technologies Educatives), locaux complémentaires d’activités d’enseignement et de recherche des UFR SPSE et SSA, ainsi que de deux DUT dépendant de l’IUT Ville d’Avray.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.904004, 2.213203)).title("Batiment CSU\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Centre Sportif\n\n"+"Annéee de construction : 1968"+
                                "\n\n"+"Activités : Centre Sportif Universitaire.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.903371, 2.211268)).title("Batiment B\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Pierre Grappin\n\n"+"Annéee de construction : 1964"+
                                "\n\n"+"Activités : Administration centrale de l’établissement (Présidence, Direction Générale des Services, Pilotage, Service juridique, Service communication, DRH, service sécurité et sûreté).\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.90448, 2.212187)).title("Batiment V\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Ida Maier\n\n"+"Annéee de construction : 2010"+
                                "\n\n"+"Activités : UFR LCE – Langues et Cultures Etrangères.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.903457, 2.217779)).title("Batiment I\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Gymnase\n\n"+"Annéee de construction : 1981"+
                                "\n\n"+"Activités : UFR STAPS – Sciences et Techniques des Activités Physiques et Sportives.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902575, 2.216131)).title("Batiment M\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Ephémère 1\n\n"+"Annéee de construction : 1995"+
                                "\n\n"+"Activités : Service de la Formation Continue.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902222, 2.211952)).title("Batiment D\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Henri Lefebvre\n\n"+"Annéee de construction : 1966"+
                                "\n\n"+"Activités : UFR SSA – Sciences Sociales et Administration.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.903811, 2.21724)).title("Batiment L\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Paul Ricoeur\n\n"+"Annéee de construction : 1995"+
                                "\n\n"+"Activités : UFR PHILLIA – Philosophie Information-communication Langages Littératures Arts du spectacle.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.904212, 2.20992)).title("Batiment MAE\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"René Ginouvès\n\n"+"Annéee de construction : 1997"+
                                "\n\n"+"Activités : Maison de l’Archéologie et de l’Ethnologie. Unité de Service et de Recherche 3225 « Maison Archéologie et Ethnologie – René Ginouvès ». Bâtiment en gestion partagée : Université Paris Nanterre, CNRS et Université Panthéon-Sorbonne.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.905312, 2.215668)).title("Batiment BU/BDIC\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Bibliothèque Universitaire\n\n"+"Annéee de construction : 1971"+
                                "\n\n"+"Activités : Bibliothèque Universitaire et Bibliothèque de Documentation Internationale Contemporaine.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902143, 2.215191)).title("Batiment BSL\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Charlotte Delbo\n\n"+"Annéee de construction : 2004"+
                                "\n\n"+"Activités : Bâtiment des Services Logistiques.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902951, 2.216878)).title("Batiment H\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Omnisport\n\n"+"Annéee de construction : 2006"+
                                "\n\n"+"Activités : UFR STAPS – Sciences et Techniques des Activités Physiques et Sportives.\n"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(48.902559, 2.213343)).title("Batiment MDE\n")
                        .snippet("Campus : Nanterre"+"\n\n"+"Nom d'usage : "+"Maison des Etudiants\n\n"+"Annéee de construction : 2010"+
                                "\n\n"+"Activités : Maison des Etudiants.\n\n"));

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.setBuildingsEnabled(false);

        }

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
//Showing Current Location Marker on Map
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if (null != locations && null != providerList && providerList.size() > 0) {
            double longitude = locations.getLongitude();
            double latitude = locations.getLatitude();
            Geocoder geocoder = new Geocoder(getApplicationContext(),
                    Locale.getDefault());
            try {
                List<Address> listAddresses = geocoder.getFromLocation(latitude,
                        longitude, 1);
                if (null != listAddresses && listAddresses.size() > 0) {
                    String state = listAddresses.get(0).getAdminArea();
                    String country = listAddresses.get(0).getCountryName();
                    String subLocality = listAddresses.get(0).getSubLocality();
                    markerOptions.title("" + latLng + "," + subLocality + "," + state
                            + "," + country);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(false);
                    }
                } else {
                    Toast.makeText(this, "permission denied",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
