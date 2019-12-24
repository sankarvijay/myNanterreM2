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
 * Created by Sankar Vijay on 28/02/2019.
 */

public class FetchDataFavLigneL extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String dataParsed2 = "";
    private String dataParsed3 = "";
    private String singleParsed2 = "";
    private String singleParsed3 = "";
    private String gareFavori = "";
    private String lien = "";

    FetchDataFavLigneL(String gare) {
        this.gareFavori = gare;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            switch (gareFavori) {
                case "GARE ST LAZARE":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738400:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "PONT CARDINET":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738111:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE CLICHY LEVALLOIS":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738112:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE D'ASNIERES":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738113:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE BECON LES BRUYERES":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738200:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DES VALLEES":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738630:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE LA GARENNE COLOMBES":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738600:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE HOUILLES CARRIERES SUR SEINE":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738640:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "Gare de Sartrouville":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738641:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE MAISONS LAFFITTE":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738642:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE D'ACHERES VILLE":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738165:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE CONFLANS FIN D'OISE":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738145:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "GARE DE NEUVILLE UNIVERSITE":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8733448:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "Gare de Cergy Préfecture":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738190:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "Gare de Cergy St Christophe":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738249:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                case "Gare de Cergy le Haut":
                    lien = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures?line_id=800:L&stop_point_id=8738265:800:L&apikey=9418584454f9adb7d232f4251aaa962477293753d8fd25acb2966fba";
                    break;
                default:
                    System.err.println("no match");
            }

            URL url = new URL(lien);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray ja = new JSONArray(data);

            for (int i = 0; i <= 6; i++) {
                JSONObject jo = (JSONObject) ja.getJSONObject(i);

                singleParsed2 = jo.get("lineDirection") + "\n";
                dataParsed2 = dataParsed2 + singleParsed2;

                singleParsed3 = jo.get("time") + " min\n";
                dataParsed3 = dataParsed3 + singleParsed3;

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        GareFavoriteLigneL.heureT.setText(this.dataParsed3);
        GareFavoriteLigneL.destination.setText(this.dataParsed2);
        GareFavoriteLigneL.direction.setText(gareFavori);
        GareFavoriteLigneL.direction2.setText("vers Nanterre-Université");

    }
}