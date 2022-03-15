package com.example.recipeappjetpackcomposelearn.dependencyinjection

import android.content.Context
import com.example.recipeappjetpackcomposelearn.presentation.BaseApplication
import com.example.recipeappjetpackcomposelearn.presentation.utils.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String {
        return "This is a random String"
    }

    @Singleton
    @Provides
    fun provideUserPreference(@ApplicationContext appContext: Context): UserPreference {
        var prefs = UserPreference
        prefs.initPrefs(appContext)
        return prefs
    }

}