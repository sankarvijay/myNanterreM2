package miage.parisnanterre.fr.mynanterre.implem;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sankar Vijay on 31/10/2019.
 */
public class FetchDataBatimentUniv extends AsyncTask<Void, Void, Void> {

    private String data = "";
    public static String dataParsed = "";
    public static String dataParsed2 = "";
    public static String dataParsed3 = "";
    public static String dataParsed4 = "";
    public static String dataParsed5 = "";
    public static String dataParsed6 = "";
    public static String dataParsed7 = "";
    private String singleParsed = "";
    private String singleParsed2 = "";
    private String singleParsed3 = "";
    private String singleParsed4 = "";
    private String singleParsed5 = "";
    private String singleParsed6 = "";
    private String singleParsed7 = "";

    public FetchDataBatimentUniv(){

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://opendata.hauts-de-seine.fr/api/records/1.0/search/?dataset=affectations-et-usages-des-batiments-de-luniversite-paris-nanterre&rows=40&sort=lettre&facet=campus&facet=lettre&facet=nom_d_usage&facet=annee_de_construction&facet=surface_shon&facet=nombre_de_niveaux_de_su_hors_parking&facet=typologie_architecturale&facet=activites&facet=description_activites");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jo = new JSONObject(data);

                JSONArray arr = (JSONArray) jo.getJSONArray("records");

                for(int j=0;j<31;j++)
                {

                    JSONObject jb1 = arr.getJSONObject(j).getJSONObject("fields");
                    if(jb1.get("campus").equals("Nanterre")){

                        singleParsed = jb1.get("lettre") + "\n";
                        dataParsed = dataParsed + singleParsed;

                        if (!jb1.has("nom_d_usage")){
                            singleParsed2 = "" + "\n";
                            dataParsed2 = dataParsed2 + singleParsed2;
                        }else{
                            singleParsed2 = jb1.get("nom_d_usage") + "\n";
                            dataParsed2 = dataParsed2 + singleParsed2;
                        }

                        singleParsed3 = jb1.get("campus") + "\n";
                        dataParsed3 = dataParsed3 + singleParsed3;

                        singleParsed4 = jb1.get("annee_de_construction") + "\n";
                        dataParsed4 = dataParsed4 + singleParsed4;

                        if (!jb1.has("description_activites")){
                            singleParsed5 = "" + "\n";
                            dataParsed5 = dataParsed5 + singleParsed5;
                        }else{
                            singleParsed5 = jb1.get("description_activites") + "\n";
                            dataParsed5 = dataParsed5 + singleParsed5;
                        }

                        singleParsed6 = jb1.get("activites") + "\n";
                        dataParsed6 = dataParsed6 + singleParsed6;

                        JSONArray jsonArray = (JSONArray) jb1.getJSONArray("coordonnees_gps");
                        singleParsed7 = jsonArray.toString() + "\n";
                        dataParsed7 = dataParsed7 + singleParsed7;
                    }
                }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
