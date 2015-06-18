package de.szut.ita13.app.schulapp.calendar.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Rene on 16.06.2015.
 */
public class CalendarNotificationReceiver extends BroadcastReceiver {

    public static final String SERIALIZABLE_KEY = "calendarnotification";
    public static final String NOTIFICATION_ID = "calendarnotificationid";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Receiver", "Received Notification!");
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(SERIALIZABLE_KEY);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);
        CalendarNotificationFactory.playNotificationSound(context);
    }

}
