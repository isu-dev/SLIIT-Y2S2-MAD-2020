package com.example.y2s2mad.Lab_Sheets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.y2s2mad.R;

public class L5_Notifications extends AppCompatActivity {

    Button l5_btn_registerForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l5__notifications);

        l5_btn_registerForm = findViewById(R.id.l5_btn_registerForm);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.common_google_play_services_notification_channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            //register the channel with the system;
            //you can't change the importance or other notification behavious after this
            final NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            l5_btn_registerForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(L5_Notifications.this, Notification.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(L5_Notifications.this, 0, intent, 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(L5_Notifications.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("My notification")
                            .setContentText("Hello World")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            //set the intent that will fire when the user taps the notification
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(L5_Notifications.this);

                    //notificationId is an unique int for each notification that you must define
                    notificationManager.notify(0, builder.build());
                }
            });
        }
    }
}