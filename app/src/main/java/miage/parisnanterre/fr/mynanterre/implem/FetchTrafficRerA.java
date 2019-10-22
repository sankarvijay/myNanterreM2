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
 * Created by Sankar Vijay on 23/02/2019.
 */
public class FetchTrafficRerA extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String dataParsed = "";
    private String dataParsed2 = "";
    private String dataParsed3 = "";
    private String dataParsed4 = "";
    private String singleParsed = "";
    private String singleParsed2 = "";
    private String singleParsed3 = "";
    private String singleParsed4 = "";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api-ratp.pierre-grimaud.fr/v3/traffic/rers/A");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jo = new JSONObject(data);
            JSONObject jo2 = jo.getJSONObject("result");

            singleParsed = jo2.get("title") + "\n";
            dataParsed = dataParsed + singleParsed;

            singleParsed2 = jo2.get("message") + "\n";
            dataParsed2 = dataParsed2 + singleParsed2;

            singleParsed3 = jo2.get("title") + "\n";
            dataParsed3 = dataParsed3 + singleParsed3;

            singleParsed4 = jo2.get("message") + "\n";
            dataParsed4 = dataParsed4 + singleParsed4;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        TrainRerA.title.setText(this.dataParsed);
        TrainRerA.info.setText(this.dataParsed2);


    }

}
