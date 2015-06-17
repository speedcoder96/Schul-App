package de.szut.ita13.app.schulapp.calendar.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;


import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;

/**
 * Created by Rene on 16.06.2015.
 */
public class CalendarNotificationFactory {

    public static final long DELAY_DAY = 1000 * 60 * 60 * 24;

    public static void createNotification(Context context, CalendarAppointment appointment, long delay) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(appointment.getSubject());
        builder.setContentText(appointment.getNote());
        builder.setSmallIcon(R.drawable.ic_launcher);
        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, CalendarNotificationReceiver.class);
        notificationIntent.putExtra(CalendarNotificationReceiver.NOTIFICATION_ID, appointment.getRefID());
        notificationIntent.putExtra(CalendarNotificationReceiver.SERIALIZABLE_KEY, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notification.contentIntent = pendingIntent;

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

    }

    public static void removeNotification(Context context, CalendarAppointment calendarAppointment) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel("Remove", (int)calendarAppointment.getRefID());
    }

    public static void playNotificationSound(Context context) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), uri);
        ringtone.play();
    }


}
