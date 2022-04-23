package com.app.share.utils

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager


object Utils {

    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    @JvmStatic
    fun showAlertDialog(
        title: String?,
        message: String?,
        context: Context?,
        callback: DialogButtonsCallback
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                R.string.ok
            ) { dialog: DialogInterface?, which: Int -> callback.onDialogPositiveClick() }
            .setIcon(R.drawable.ic_dialog_alert)
            .setCancelable(false)
            .show()
    }


    interface DialogButtonsCallback {
        fun onDialogPositiveClick()
    }

}