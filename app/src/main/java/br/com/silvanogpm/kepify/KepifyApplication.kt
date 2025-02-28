package br.com.silvanogpm.kepify

import android.app.Application
import br.com.silvanogpm.kepify.di.networkModule
import br.com.silvanogpm.kepify.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class KepifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KepifyApplication)
            modules(networkModule, viewModelModule)
        }
    }
}