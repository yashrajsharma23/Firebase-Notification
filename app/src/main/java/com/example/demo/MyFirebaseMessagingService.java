package com.example.demo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Context ctx;
    String message, status, title;
    Intent notificationIntent;
    int NOTIF_ID = 0;
    String CHANNEL_ID = "notification_test";

    String  name;
    Notification notif = null;

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        ctx = this;
        String Data = remoteMessage.getData().toString();
        if (!Data.equals("")) {
            message = remoteMessage.getData().get("message");
            status = remoteMessage.getData().get("status");
            title = remoteMessage.getData().get("title");
            PendingIntent pendingIntent = PendingIntent.
                    getActivity(this, NOTIF_ID, notificationIntent,
                            PendingIntent.FLAG_ONE_SHOT);
            NotificationManager notificationManager =
                    (NotificationManager) this.
                            getSystemService(Context.NOTIFICATION_SERVICE);

            notificationIntent = new Intent(MyFirebaseMessagingService.this,
                    NotificationDataScreen.class);
            notificationIntent.putExtra("title", title);
            notificationIntent.putExtra("message", message);
            notificationIntent.putExtra("status", status);

            notif = new Notification.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setSmallIcon(getNotificationIcon())
                    .setChannelId(CHANNEL_ID)
                    .setColor(getResources().getColor(R.color.colorAccent))
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .build();
            notif.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(NOTIF_ID, notif);

            sendNotification(title, message, status, remoteMessage);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendNotification(String noti_title, String noti_message, String noti_status, RemoteMessage remoteMessage) {//,String noti_token) {

        Notification notif = null;
        String title = noti_title;
        String message = noti_message;
        String status = noti_status;

        //Low balance notification

                notificationIntent = new Intent(MyFirebaseMessagingService.this, NotificationDataScreen.class);
                notificationIntent.putExtra("title", title);
                notificationIntent.putExtra("message", message);
                notificationIntent.putExtra("status", status);


            PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIF_ID, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                /* Create or update. */
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, this.getResources().getString(R.string.app_name),
                        NotificationManager.IMPORTANCE_DEFAULT);

                mChannel.enableLights(true);
                mChannel.setLightColor(Color.GREEN);
                mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(mChannel);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notif = new Notification.Builder(this)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(title)
                            .setSmallIcon(getNotificationIcon())
                            .setSound(uri)
                            .setChannelId(CHANNEL_ID)
                            .setColor(getResources().getColor(R.color.colorAccent))
                            .setStyle(new Notification.BigTextStyle().bigText(message))
                            .build();
                    notif.flags |= Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(NOTIF_ID, notif);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    notif = new Notification.Builder(this)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(title)
                            .setSmallIcon(getNotificationIcon())
                            .setSound(uri)
                            .setColor(getResources().getColor(R.color.colorAccent))
                            .setStyle(new Notification.BigTextStyle().bigText(message))
                            .build();
                    notif.flags |= Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(NOTIF_ID, notif);
                    //disconnect(true);

                } else {
                    notif = new Notification.Builder(this)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(title)
                            .setSmallIcon(getNotificationIcon())
                            .setSound(uri)
                            .setColor(getResources().getColor(R.color.colorAccent))
                            .setStyle(new Notification.BigTextStyle().bigText(message))
                            .build();
                    notif.flags |= Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(NOTIF_ID, notif);
                }
            } else {

                Notification ntfL = new Notification.Builder(this)
                        .setSmallIcon(getNotificationIcon())
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setSound(uri)
                        .getNotification();
            }
    }

    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher_round : R.mipmap.ic_launcher_round;
    }

}
