package com.holat.holat.ui.dynamic.views.initialize

import android.annotation.SuppressLint
import android.app.Activity
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.holat.holat.ui.dynamic.models.GlobalView

/**
Created by Mohamed Mohamed Taha on 10/31/2024
 */
@SuppressLint("SetTextI18n")
fun Activity.drawTitle(name: String): TextView {
    val initializeTextView = initializeTextView()
    initializeTextView.textSize = 15f
    initializeTextView.visibility = View.VISIBLE
    val spannableStringBuilder = SpannableStringBuilder()
//    val questToolTip = name
    val questToolTipSpan = SpannableString(name)
    questToolTipSpan.setSpan(
        RelativeSizeSpan(0.7f),
        0, name.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    questToolTipSpan.setSpan(
        ForegroundColorSpan(
            ContextCompat.getColor(
                this,
                android.R.color.holo_red_dark
            )
        ), // Change to your desired color
        0, name.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannableStringBuilder.append(questToolTipSpan)
    initializeTextView.text = spannableStringBuilder
    initializeTextView.movementMethod = LinkMovementMethod.getInstance()
    return initializeTextView
}
@SuppressLint("SetTextI18n")
fun Activity.drawTitle(globalView: GlobalView): TextView {
    val initializeTextView = initializeTextView()
    initializeTextView.textSize = 15f
    initializeTextView.visibility = View.VISIBLE
    val spannableStringBuilder = SpannableStringBuilder()
    val questToolTip = globalView.tablesModel.questToolTip
    val questToolTipSpan = SpannableString(questToolTip)
    questToolTipSpan.setSpan(
        RelativeSizeSpan(0.7f),
        0, questToolTip.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    questToolTipSpan.setSpan(
        ForegroundColorSpan(
            ContextCompat.getColor(
                this,
                android.R.color.holo_red_dark
            )
        ), // Change to your desired color
        0, questToolTip.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannableStringBuilder.append(globalView.tablesModel.questCode)
    spannableStringBuilder.append(globalView.tablesModel.questName)
    spannableStringBuilder.append("\n")
    spannableStringBuilder.append(questToolTipSpan)
    initializeTextView.text = spannableStringBuilder
    initializeTextView.movementMethod = LinkMovementMethod.getInstance()
    return initializeTextView
}