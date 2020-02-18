package io.mns.rebelapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.mns.rebelapp.databinding.ActivityMainBinding;

import static io.mns.rebelapp.RebelService.DAYS;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String imgUrl = "https://nwscdn.com/media/catalog/product/cache/all/thumbnail/800x/9df78eab33525d08d6e5fb8d27136e95/t/r/training_football_in_orange.jpg";

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setDays(diffDate());



        // try cool stuff

        Glide.with(this)
                .load(imgUrl)
                .into(binding.image);

        binding.image.setOnClickListener(v -> {

        });




        // cool stuff over



        /*JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        PersistableBundle bundle = new PersistableBundle();
        bundle.putLong(DAYS, diffDate());
        if (jobScheduler != null)
            jobScheduler.schedule(
                    new JobInfo.Builder(0, new ComponentName(this, RebelService.class))
                            .setPeriodic(1000 * 60)
                            .setPersisted(true)
                            .setExtras(bundle)
                            .build()
            );
        startService(new Intent(this, RebelService.class));*/
    }

    private long diffDate() {
        Date finalDate = parseDate("2019-11-09");
        Date now = new Date();
        long diff = 0;
        if (finalDate != null)
            diff = finalDate.getTime() - now.getTime();
        return (diff / (1000 * 60 * 60 * 24));
    }
}
