package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notificationsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var builder: Notification.Builder
    
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, NotificationActivity::class.java)

        binding.btnNotification.setOnClickListener{

            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_baseline_notifications_24))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(binding.etNotification.text!!)
            } else {

                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_baseline_notifications_24))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(binding.etNotification.text!!)
            }
            notificationManager.notify(1234, builder.build())
        }
    }
}