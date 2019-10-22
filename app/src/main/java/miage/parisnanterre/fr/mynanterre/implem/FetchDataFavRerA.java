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
 * Created by Sankar Vijay on 23/02/2019.
 */

public class FetchDataFavRerA extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String dataParsed = "";
    private String dataParsed2 = "";
    private String dataParsed3 = "";
    private String singleParsed = "";
    private String singleParsed2 = "";
    private String singleParsed3 = "";
    private String gareFavori = "";
    private String lien = "";

    FetchDataFavRerA(String gare) {
        this.gareFavori = gare;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            switch (gareFavori) {
                case "Saint-Germain-en-Laye":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/saint+germain+en+laye/R";
                    break;
                case "Le Vesinet-Le Pecq":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/le+vesinet+le+pecq/R";
                    break;
                case "Le Vesinet-Centre":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/le+vesinet+centre/R";
                    break;
                case "Chatou-Croissy":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/chatou+croissy/R";
                    break;
                case "Rueil-Malmaison":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/rueil+malmaison/R";
                    break;
                case "Nanterre-Ville":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/nanterre+ville/R";
                    break;
                case "Cergy-Le-Haut":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/cergy+le+haut/R";
                    break;
                case "Cergy-Saint-Christophe":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/cergy+saint+christophe/R";
                    break;
                case "Cergy-Prefecture":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/cergy+prefecture/R";
                    break;
                case "Neuville-Universite":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/neuville+universite/R";
                    break;
                case "Conflans-Fin d'Oise":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/conflans+fin+d'oise/R";
                    break;
                case "Acheres-Ville":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/acheres+ville/R";
                    break;
                case "Poissy":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/poissy/R";
                    break;
                case "Acheres Grand Cormier":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/acheres+grand+cormier/R";
                    break;
                case "Maisons-Laffitte":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/maisons+laffitte/R";
                    break;
                case "Sartrouville":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/sartrouville/R";
                    break;
                case "Houilles Carrieres-sur-Seine":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/houilles+carrieres+sur+seine/R";
                    break;
                case "Nanterre-Prefecture":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/nanterre+prefecture/A";
                    break;
                case "La Defense (Grande Arche)":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/la+defense+(grande+arche)/A";
                    break;
                case "Charles de Gaulle-Etoile":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/charles+de+gaulle+etoile/A";
                    break;
                case "Auber":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/auber/A";
                    break;
                case "Chatelet-Les-Halles":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/chatelet+les+halles/A";
                    break;
                case "Gare de Lyon":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/gare+de+lyon/A";
                    break;
                case "Nation":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/nation/A";
                    break;
                case "Vincennes":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/vincennes/A";
                    break;
                case "Val de Fontenay":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/val+de+fontenay/A";
                    break;
                case "Neuilly-Plaisance":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/neuilly+plaisance/A";
                    break;
                case "Bry-sur-Marne":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/bry+sur+marne/A";
                    break;
                case "Noisy-le-Grand (Mont d'Est)":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/noisy+le+grand+(mont+d'est)/A";
                    break;
                case "Noisy-Champs":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/noisy+champs/A";
                    break;
                case "Noisiel":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/noisiel/A";
                    break;
                case "Lognes":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/lognes/A";
                    break;
                case "Torcy":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/torcy/A";
                    break;
                case "Bussy-Saint-Georges":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/bussy+saint+georges/A";
                    break;
                case "Val d'Europe":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/val+d'europe/A";
                    break;
                case "Marne-la-Vallee Chessy":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/marne+la+vallee+chessy/A";
                    break;
                case "Fontenay-sous-Bois":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/fontenay+sous+bois/A";
                    break;
                case "Nogent-sur-Marne":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/nogent+sur+marne/A";
                    break;
                case "Joinville-le-Pont":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/joinville+le+pont/A";
                    break;
                case "Saint-Maur Creteil":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/saint+maur+creteil/A";
                    break;
                case "Le Parc de Saint-Maur":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/le+parc+de+saint+maur/A";
                    break;
                case "Champigny":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/champigny/A";
                    break;
                case "La Varenne-Chennevieres":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/la+varenne+chennevieres/A";
                    break;
                case "Sucy Bonneuil":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/sucy+bonneuil/A";
                    break;
                case "Boissy-Saint-Leger":
                    lien = "https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/boissy+saint+leger/A";
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

            JSONObject jo = new JSONObject(data);

            for (int i = 0; i <= data.length(); i++) {
                JSONArray arr = (JSONArray) jo.getJSONObject("result").getJSONArray("schedules");

                singleParsed = arr.getJSONObject(i).get("message") + "\n";

                dataParsed = dataParsed + singleParsed;

                singleParsed2 = arr.getJSONObject(i).get("code") + "\n";
                dataParsed2 = dataParsed2 + singleParsed2;

                singleParsed3 = arr.getJSONObject(i).get("destination") + "\n";
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

        GareFavoriteRerA.code.setText(this.dataParsed2);
        GareFavoriteRerA.heureT.setText(this.dataParsed);
        GareFavoriteRerA.destination.setText(this.dataParsed3);
        GareFavoriteRerA.direction.setText(gareFavori);
        GareFavoriteRerA.direction2.setText("vers Nanterre-Université");

    }
}