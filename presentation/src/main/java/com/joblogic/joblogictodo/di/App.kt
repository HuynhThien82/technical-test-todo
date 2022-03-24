package com.joblogic.joblogictodo.di

import android.app.Application
import com.joblogic.joblogictodo.common.di.commonModule
import com.joblogic.joblogictodo.product.di.productModule
import com.joblogic.joblogictodo.user.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(commonModule, productModule, userModule)
        }
    }
}