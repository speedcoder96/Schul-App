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
import android.util.Log;
import android.widget.Toast;
import java.util.GregorianCalendar;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.Calendar;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;

/**
 * Created by Rene on 16.06.2015.
 */
public class CalendarNotificationFactory {

    public static final String TAG = CalendarNotificationFactory.class.getSimpleName();

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
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, CalendarNotificationReceiver.class);
        notificationIntent.putExtra(CalendarNotificationReceiver.NOTIFICATION_ID, (int)appointment.getRefID());
        notificationIntent.putExtra(CalendarNotificationReceiver.SERIALIZABLE_KEY, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                (int) appointment.getRefID(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = pendingIntent;


        long currentMillis = System.currentTimeMillis();
        long futureInMillis = futureMillis(currentMillis, appointment);

        Log.d(TAG, "Delay: " + (futureInMillis - currentMillis) + " ms");
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
                targetTime.getHours(), targetTime.getMinutes(), 0);
        long targetMillis = target.getTimeInMillis();
        long difference = targetMillis - currentMillis;
        if(difference > DAY_IN_MILLIS) {
            Log.d(TAG, "Set over 1 Day Notification Delay");
            createNotificationDelayToast(difference, DAY_IN_MILLIS).show();
            return targetMillis - DAY_IN_MILLIS;
        } else if(difference > HALF_DAY_IN_MILLIS) {
            Log.d(TAG, "Set over 12 Hours Notification Delay");
            createNotificationDelayToast(difference, HALF_DAY_IN_MILLIS).show();
            return targetMillis - HALF_DAY_IN_MILLIS;
        } else if(difference > QUARTER_DAY_IN_MILLIS) {
            Log.d(TAG, "Set over 6 Hours Notification Delay");
            createNotificationDelayToast(difference, QUARTER_DAY_IN_MILLIS).show();
            return targetMillis - QUARTER_DAY_IN_MILLIS;
        } else if(difference > ONE_HOUR_IN_MILLIS) {
            Log.d(TAG, "Set over 1 Hour Notification Delay");
            createNotificationDelayToast(difference, ONE_HOUR_IN_MILLIS).show();
            return targetMillis - ONE_HOUR_IN_MILLIS;
        }
        Log.d(TAG, "Set no Notification");
        return -1;
    }

    public static void removeNotification(Context context, CalendarAppointment calendarAppointment) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel((int) calendarAppointment.getRefID());
        Intent intent = new Intent(context, CalendarNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) calendarAppointment.getRefID(),
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Log.d(TAG, "Notification " + (int) calendarAppointment.getRefID() + " removed!");
    }

    public static void playNotificationSound(Context context) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), uri);
        ringtone.play();
        Log.d(TAG, "Play Notification Sound");
    }

    private static Toast createNotificationDelayToast(long difference, long delay) {
        return Toast.makeText(Calendar.getCalendarActivity().getApplicationContext(),
                "Sie werden in " + inDayFormatString(difference - ONE_HOUR_IN_MILLIS) + " Tagen benachrichtigt!",
                Toast.LENGTH_LONG);
    }

    private static String inDayFormatString(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long currentSeconds = seconds % 60;
        long currentMinutes = minutes % 60;
        long currentHours = hours % 24;
        return fillMissingDigit(days) + ":" +
                fillMissingDigit(currentHours) + ":" +
                fillMissingDigit(currentMinutes) + ":" +
                fillMissingDigit(currentSeconds);
    }

    private static String fillMissingDigit(long value) {
        return (value < 10) ? "0" + value : String.valueOf(value);
    }


}
