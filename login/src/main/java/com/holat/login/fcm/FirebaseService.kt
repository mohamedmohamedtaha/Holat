package com.holat.login.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.holat.login.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.holat.login.LoginActivity

/**
Created by Mohamed Mohamed Taha on 2/7/2024
 */
class FirebaseService: FirebaseMessagingService() {
    private var channelId = "1"
    private lateinit var notificationManager: NotificationManager
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //Check if message contains a data payload
        if (message.data.isNotEmpty()){
            Log.d("TAG","${message.data}")
            showNotification()

        }
        // Check if message contains a notification payload

        message.notification?.let {  }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
    private fun showNotification(){

        val intent = Intent(this, LoginActivity::class.java)
        val resultPEnding:PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)}
            val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.reload)
            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this,channelId)
                .setLargeIcon(largeIcon)
                .setSmallIcon(com.chivorn.smartmaterialspinner.R.drawable.smsp_logo)
                .setContentText("Hello")
                .setAutoCancel(true)
                .setSound(soundUri)
                .setStyle(NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPEnding)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val note = notificationBuilder.build()
        notificationManager.notify(0,note)


    }

    override fun onCreate() {
        super.onCreate()
         notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel()
        }
        val notificationCompact  = NotificationCompat.Builder(applicationContext,channelId)
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createChannel(){
        val channel = NotificationChannel(channelId,"Sama Care", NotificationManager.IMPORTANCE_DEFAULT)
        channel.shouldVibrate()
        notificationManager.createNotificationChannel(channel)
    }

}



































