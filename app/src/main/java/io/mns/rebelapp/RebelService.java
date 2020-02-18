package io.mns.rebelapp;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class RebelService extends JobService {
    public static final String DAYS = "days";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        long days = jobParameters.getExtras().getLong(DAYS);
        Log.d("rebellion", "onStartJob: job works");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DAYS);
        Bitmap rebel = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rebel);
        builder.setLargeIcon(rebel)
                .setSmallIcon(R.drawable.ic_rebel)
                .setContentText(days + " to go")
                .setContentTitle("rebellion")
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(getResources().getColor(R.color.colorAccent, getTheme()))
                .setColorized(true)
                .setLights(getResources().getColor(R.color.colorAccent, getTheme()), 1000, 500);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager != null)
            manager.notify(7, builder.build());

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
