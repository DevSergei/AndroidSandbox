package android.base.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

public class MyWeatherIntentService extends IntentService implements Constants{

    private int messageId = 0;

    public MyWeatherIntentService() {
        super("MyWeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_TEMP.equals(action)) {
                final String param = intent.getStringExtra(EXTRA_DFLT_CITY);
                handleActionTemp(param);
            } else if (ACTION_PRESS.equals(action)) {
                final String param = intent.getStringExtra(EXTRA_DFLT_CITY);
                handleActionPress(param);
            }
        }
    }

    private void handleActionTemp(String param) {
        Toast.makeText(getApplicationContext(), "Getting Temperature in separate thread", Toast.LENGTH_LONG).show(); // it doesn't work!
        Log.i("weathertag","Getting Temperature in separate thread");
        makeNote("Getting Temperature in separate thread"); // it doesn't work either! Saying " Failed to post notification on channel 2 "

    }

    private void handleActionPress(String param) {
        Toast.makeText(this, "Getting Pressure in separate thread", Toast.LENGTH_LONG).show();
    }

    // вывод уведомления в строке состояния
    private void makeNote(String message) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "2")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Main service notification")
                        .setContentText(message);
        Intent resultIntent = new Intent(this, MyWeatherIntentService.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}
