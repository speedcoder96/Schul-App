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
import android.util.Log;


import java.util.GregorianCalendar;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Rene on 16.06.2015.
 */
public class CalendarNotificationFactory {

    public static long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    public static long HALF_DAY_IN_MILLIS = DAY_IN_MILLIS / 2;
    public static long QUARTER_DAY_IN_MILLIS = DAY_IN_MILLIS / 4;
    public static long ONE_HOUR_IN_MILLIS = DAY_IN_MILLIS / 24;

    public static long NO_NOTIFICATION_REQUIRED = -1;

    public static void createNotification(Context context, CalendarAppointment appointment) {
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

        long futureInMillis = futureMillis(System.currentTimeMillis(), appointment);
        Log.d("Factory", "Current:" + System.currentTimeMillis() + " Target: " + futureInMillis);
        if(futureInMillis != NO_NOTIFICATION_REQUIRED) {
            AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
        }
    }

    private static long futureMillis(long currentMillis, CalendarAppointment calendarAppointment) {
        GregorianCalendar target = new GregorianCalendar();
        CalendarDate targetDate = calendarAppointment.getCalendarDate();
        CalendarTime targetTime = calendarAppointment.getStartTime();
        target.set(targetDate.getYear(), targetDate.getMonth() - 1, targetDate.getDay(),
                targetTime.getHours(), targetTime.getMinutes());
        GregorianCalendar current = new GregorianCalendar();
        current.setTimeInMillis(currentMillis);
        Log.d("Factory", "Date: " + current.toString());

        long targetMillis = target.getTimeInMillis();
        long difference = targetMillis - currentMillis;
        Log.d("Factory", "Difference: " + difference);
        if(difference > DAY_IN_MILLIS) {
            return targetMillis - DAY_IN_MILLIS;
        } else if(difference > HALF_DAY_IN_MILLIS) {
            return targetMillis - HALF_DAY_IN_MILLIS;
        } else if(difference > QUARTER_DAY_IN_MILLIS) {
            return targetMillis - QUARTER_DAY_IN_MILLIS;
        } else if(difference > ONE_HOUR_IN_MILLIS) {
            return targetMillis - ONE_HOUR_IN_MILLIS;
        }
        return -1;
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
