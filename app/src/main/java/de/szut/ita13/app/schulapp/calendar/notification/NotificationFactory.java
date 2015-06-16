package de.szut.ita13.app.schulapp.calendar.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.views.CalendarActivity;

/**
 * Created by Rene on 16.06.2015.
 */
public class NotificationFactory {

    public static final int NOTIFICATION = 1;

    public static void createNotification(long refID, Context context, long delay, String title, String message) {
        Intent intent = new Intent(context, CalendarActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Termin")
                .setContentText("Test")
                .setSmallIcon(R.drawable.ic_action_accept)
                .setContentIntent(pendingIntent)
                .build();

        //intent.putExtra(NOTIFICATION, notification);
        //TODO an Notification arbeiten und diese für die einzelnen Termine feuern können
        //TODO Verknüpfung durch die RefID des Appointments (Termines)
    }

}
