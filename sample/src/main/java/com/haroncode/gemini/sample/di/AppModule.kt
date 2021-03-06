package com.haroncode.gemini.sample.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.haroncode.gemini.sample.data.ConnectivityRepositoryImpl
import com.haroncode.gemini.sample.domain.repository.AuthRepository
import com.haroncode.gemini.sample.domain.repository.AuthRepositoryImpl
import com.haroncode.gemini.sample.domain.repository.ConnectivityRepository
import com.haroncode.gemini.sample.presentation.routing.HandlerRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {

        // Navigation
        val cicerone = Cicerone.create(HandlerRouter(Handler(Looper.getMainLooper())))
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        // Global
        bind(Context::class.java).toInstance(context)
        bind(ConnectivityRepository::class.java).to(ConnectivityRepositoryImpl::class.java).singleton()
        bind(AuthRepository::class.java).to(AuthRepositoryImpl::class.java).singleton()
    }
}
