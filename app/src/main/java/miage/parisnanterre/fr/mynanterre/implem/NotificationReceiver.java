package miage.parisnanterre.fr.mynanterre.implem;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import miage.parisnanterre.fr.mynanterre.R;

/**
 * Created by Sankar Vijay on 24/12/2019.
 */
public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,TrainRerA.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Bundle extras = intent.getExtras();
        String traffic = extras.getString("trafic");

        //if we want ring on notifcation then uncomment below line//
          Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setSmallIcon(R.drawable.logomynanterre).
                setContentIntent(pendingIntent).
                setContentText(traffic).
                setContentTitle("Infos traffic Rer A (myNanterre)").
                setSound(alarmSound).
        setAutoCancel(true);
        notificationManager.notify(100,builder.build());

    }
}