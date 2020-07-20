package com.example.android.droidcafe;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tampilanorder extends AppCompatActivity {
    private static final String PRIMARY_CHANNEL_ID ="primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotificationManager;

    //Fungsi notif
    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID)
                .setContentTitle("Sepatuku")
                .setContentText("Pre Order sukses tunggu admin menghubungi anda")
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.warna);
        return notifyBuilder;

    }
    //fungsi untuk mengirim notifikasi
    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
    }
    //Fungsi membuat notifikasi
    public void createNotificationChannel()
    {
        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //buat notif
        NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                "Mascot Notification",NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.WHITE);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("Notifikasi dari admin");
        mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_tampilanorder);
        TextView tpnama = findViewById(R.id.tp_nama);
        TextView tpalamat =findViewById(R.id.tp_alamat);
        TextView tpphone =findViewById(R.id.tp_phone);
        TextView tpsize =findViewById(R.id.tp_size);
        TextView tpemail=findViewById(R.id.tp_email);
        Button btnsls=findViewById(R.id.selesai);

        Bundle bundle=getIntent().getExtras();
        String n=bundle.getString("nama");
        tpnama.setText(n);
        String a=bundle.getString("alamat");
        tpalamat.setText(a);
        String p=bundle.getString("phone");
        tpphone.setText(p);
        String s=bundle.getString("size");
        tpsize.setText(s);
        String e=bundle.getString("email");
        tpemail.setText(e);
        btnsls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
                Toast.makeText(getApplicationContext(),"Kamu berhasil Pre order tunggu admin menghubungi anda",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(tampilanorder.this,MainActivity.class);
                startActivity(intent);
            }
        });






    }
}