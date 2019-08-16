package com.tistory.black_jin0427.navermovie

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.tistory.black_jin0427.navermovie.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    companion object {

        var DEBUG = false
    }

    override fun onCreate() {
        super.onCreate()

        DEBUG = isDebuggable(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    /**
     * get Debug Mode
     *
     * @param context
     * @return
     */
    private fun isDebuggable(context: Context): Boolean {

        var debuggable = false

        val pm = context.packageManager
        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = 0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
            /* debuggable variable will remain false */
        }

        return debuggable
    }
}