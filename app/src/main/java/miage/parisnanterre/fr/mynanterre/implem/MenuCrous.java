package miage.parisnanterre.fr.mynanterre.implem;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.ProgressDialog;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import miage.parisnanterre.fr.mynanterre.R;

public class MenuCrous extends AppCompatActivity {

    ListView myRss;
    ArrayList<String> menus;
    ArrayList<String> links;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menucrous);


     //   myRss = (ListView) findViewById(R.id.testXML);

     //   menus = new ArrayList<String>();
      //  links = new ArrayList<String>();


      //  new ProcessInBackground().execute();


    }


 /*
    public InputStream getInputStream(URL url)
    {
        try{
            return  url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }




    public class ProcessInBackground extends AsyncTask<Integer, Void, String>
    {
        ProgressDialog progressDialog = new ProgressDialog(MenuCrous.this);
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("En cours");
            progressDialog.show();

        }

        @Override
        protected String doInBackground(Integer... integers) {
            try
            {
                URL url = new URL("http://webservices-v2.crous-mobile.fr:8080/feed/versailles/externe/menu.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF_8");
                boolean insideItem = false;
                int eventType = xpp.getEventType();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("resto") && xpp.getAttributeValue(null,"id").equals("r52"))
                        {
                            // xpp.getAttributeValue(null,"r42");
                            insideItem = true;

                        }
                        else if (xpp.getName().equalsIgnoreCase("menu") && xpp.getAttributeValue(null,"date").equals(dateFormat.format(date)))
                        {

                            if (insideItem)
                            {
                                menus.add(xpp.nextText());

                                int token = xpp.nextToken();
                                while(token!=XmlPullParser.CDSECT)
                                {
                                    token = xpp.nextToken();
                                }
                                String cdata = xpp.getText();
                                String result = cdata.substring(cdata.indexOf("<h2>"), (cdata.lastIndexOf("<h2>soir</h2>")));
                                Log.i("Info", result);

                                System.out.println(HtmlCompat.fromHtml(result, HtmlCompat.FROM_HTML_MODE_LEGACY));


                            }
                        }


                    }
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("resto"))
                    {
                        insideItem = false;
                    }
                    eventType = xpp.next();

                }

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
       protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MenuCrous.this, android.R.layout.simple_list_item_1, menus);
            myRss.setAdapter(adapter);
            progressDialog.dismiss();

        }
    }


 */




}
