package com.holat.login.utils

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.holat.login.R

object DialogUtil {
    @JvmStatic
    fun showMessageWithYesNoMaterialDesign(
        context: Context,
        title: String,
        message: String,
        clickListener: DialogInterface.OnClickListener
    ) {
        val builder = MaterialAlertDialogBuilder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setPositiveButton(context.getString(R.string.yes), clickListener)
        builder.setNegativeButton(context.getString(R.string.no), null)
        val dialog = builder.create()
        dialog.show()
    }

    @JvmStatic
    fun showMessageWithYesNoMaterialDesign(
        context: Context,
        title: String,
        message: String,
        yesListener: DialogInterface.OnClickListener, noListener: DialogInterface.OnClickListener
    ) {
        val builder = MaterialAlertDialogBuilder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setPositiveButton(context.getString(R.string.yes), yesListener)
        builder.setNegativeButton(context.getString(R.string.no), noListener)
        val dialog = builder.create()
        dialog.show()
    }

    @JvmStatic
    fun showMessageWithOkMaterialDesign(
        context: Context,
        title: String,
        message: String
    ) {
        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setPositiveButton(context.getString(R.string.dialog_ok), null)
        val dialog = builder.create()
        dialog.show()
    }


    @JvmStatic
    fun showErrorDialog(context: Context, message: String) {
        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.setMessage(message)
        builder.setTitle(context.getString(R.string.error))
        builder.setPositiveButton(context.getString(R.string.yes), null)
        val dialog = builder.create()
        dialog.show()
    }

    @JvmStatic
    fun showSuccessDialog(context: Context, message: String) {
        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.setMessage(message)
        builder.setTitle(context.getString(R.string.messages))
        val dialog = builder.create()
        dialog.show()
    }

    @JvmStatic
    fun showSuccessDialogWithOk(context: Context, message: String) {
        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.setMessage(message)
        builder.setTitle(context.getString(R.string.messages))
        builder.setPositiveButton(context.getString(R.string.yes), null)
        val dialog = builder.create()
        dialog.show()
    }

    @JvmStatic
    fun showMessageWithOkListenerMaterialDesign(
        context: Context,
        title: String,
        message: String, okListener: DialogInterface.OnClickListener
    ) {
        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setPositiveButton(context.getString(R.string.dialog_ok), okListener)
        val dialog = builder.create()
        dialog.show()
    }

    @JvmStatic
    fun showInfoDialog(context: Context, message: String) {
        val builder = MaterialAlertDialogBuilder(context, R.style.CustomMaterialDialog)
        builder.setMessage(message)
        builder.setTitle(context.getString(R.string.info_dialog_title))
        builder.setPositiveButton(context.getString(R.string.yes), null)
        val dialog = builder.create()
        dialog.show()
    }

}