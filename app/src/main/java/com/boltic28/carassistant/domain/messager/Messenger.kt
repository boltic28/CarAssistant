package com.boltic28.carassistant.domain.messager

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class Messenger(private val context: Context) {

    fun showMessage(@StringRes msgID: Int, vararg: Any? = null){
        Toast.makeText(context, context.resources.getString(msgID, vararg), Toast.LENGTH_SHORT).show()
    }

    fun showMessage(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}