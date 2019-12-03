package miage.parisnanterre.fr.mynanterre.implem;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.UserTimeline;

import miage.parisnanterre.fr.mynanterre.R;

public class LiveTweet extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_tweets);


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
