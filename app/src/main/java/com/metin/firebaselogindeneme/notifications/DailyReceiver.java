package com.metin.firebaselogindeneme.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.metin.firebaselogindeneme.MoodTrackingActivity;
import com.metin.firebaselogindeneme.R;

public class DailyReceiver extends BroadcastReceiver {
    private NotificationCompat.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager bildirimYoneticisi = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, MoodTrackingActivity.class);

        PendingIntent gidilecekIntent = PendingIntent.getActivity(context,1,intent1,PendingIntent.FLAG_UPDATE_CURRENT);


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            String kanalId = "kanalId";
            String kanalAd = "kanalAd";
            String kanalTanim = "kanalTanim";
            int kanalOnceligi = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);
            if (kanal == null){
                kanal = new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                kanal.setDescription(kanalTanim);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(context,kanalId);
            builder.setContentTitle("Harekete Geç");
            builder.setContentText("Nasılsın? Bildirime Tıkla ve Ruh Halini Seç.");
            builder.setSmallIcon(R.drawable.yogi2);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);

        }else {
            builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("Başlık");
            builder.setContentText("İçerik");
            builder.setSmallIcon(R.drawable.yogi2);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        bildirimYoneticisi.notify(1,builder.build());

    }

}