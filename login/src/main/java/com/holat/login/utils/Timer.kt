package com.holat.login.utils

import android.os.CountDownTimer
import androidx.appcompat.widget.AppCompatTextView
import javax.inject.Inject

/**
Created by Mohamed Mohamed Taha on 1/24/2024
 */
class Timer @Inject constructor(private val finishListener: FinishListener){
    private lateinit var timer: CountDownTimer
    //This method for set time for Reset the password
     fun startCountdownTimer(appCompatTextView: AppCompatTextView,timePerSecond:Long = 120 ) {
        val time:Long = timePerSecond * 1000
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                appCompatTextView.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                finishListener.finish()
                stopCountdownTimer()
            }
        }
        if (::timer.isInitialized){
            timer.start()
        }
    }
    fun startCountdownTimer() {
        val time:Long = 10 * 1000
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                finishListener.finish()
                stopCountdownTimer()
            }
        }
        if (::timer.isInitialized)
            timer.start()
    }

     fun stopCountdownTimer() {
        if (::timer.isInitialized)
            timer.cancel()
    }
}