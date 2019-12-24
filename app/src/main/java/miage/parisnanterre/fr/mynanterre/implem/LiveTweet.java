package miage.parisnanterre.fr.mynanterre.implem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.UserTimeline;

import miage.parisnanterre.fr.mynanterre.R;

public class LiveTweet extends ListActivity   {


    ImageView retour;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_tweets);

        retour = (ImageView) findViewById(R.id.back);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ListeCrous.class);
                startActivity(myIntent);
            }
        });


        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("gb8GzzQkm3S3EripzroUQHG1O", "9aPqxcAgKD6vaFca0RyUxipWxj027YmW2Zhs57LzFLxdZX3aRR"))
                .debug(true)
                .build();
        Twitter.initialize(config);
        // a renouveler le 10 janvier

        // Twitter.initialize(this);

        TweetUi.getInstance(); // TweetUi for displaying Tweets and Timelines


        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("UParisNanterre")
                .includeRetweets(false)
                .includeReplies(false)
                .maxItemsPerRequest(10)
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);










    }




}



