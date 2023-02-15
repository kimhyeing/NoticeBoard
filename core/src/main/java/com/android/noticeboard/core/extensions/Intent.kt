package com.android.noticeboard.core.extensions

import android.content.Intent
import android.os.Build
import java.io.Serializable

fun <T : Serializable?> Intent.getSerializableWithType(name: String, clazz: Class<T>): T {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializableExtra(name, clazz)!!
    else
        this.getSerializableExtra(name) as T
}
