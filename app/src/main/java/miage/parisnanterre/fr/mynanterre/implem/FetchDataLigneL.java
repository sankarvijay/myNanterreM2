package miage.parisnanterre.fr.mynanterre.implem;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sankar Vijay on 27/02/2019.
 */
public class FetchDataLigneL extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String dataParsed = "";
    private String dataParsed2 = "";
    private String singleParsed = "";
    private String singleParsed2 = "";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://opendata.stif.info/service/api-stif/stop_areas/stop_area:0:SA:8738631/lines/line:0:800:L/departures?apikey=5d0d557449ff837340641eb32c3987514b17fce46afe243a115cddb2&arrivalId=stop_area:0:SA:8738265&departureId=stop_area:0:SA:8738631");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jo = new JSONObject(data);

            for (int i = 0; i < 8; i++) {
                JSONObject ob = (JSONObject) jo.getJSONArray("departures").getJSONObject(i);

                singleParsed = ob.getJSONObject("display_informations").get("direction") + "\n";


                singleParsed2 = ob.getJSONObject("stop_date_time").get("departure_date_time") + "\n";
                String[] dateTime = singleParsed2.split("T");

                String s1 = dateTime[1].substring(0, 2);
                String s2 = dateTime[1].substring(2, 4);

                String dashedString = s1 + ":" + s2 + "\n";

                dataParsed = dataParsed + singleParsed;
                dataParsed2 = dataParsed2 + dashedString;

            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        TrainLigneL.destination.setText(this.dataParsed);
        TrainLigneL.heureT.setText(this.dataParsed2);


    }
}