package org.adaschool.taskplanner.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.adaschool.taskplanner.analytics.AnalyticsAdapter
import org.adaschool.taskplanner.analytics.FlurryAnalyticsAdapter
import org.adaschool.taskplanner.data.AppDatabase
import org.adaschool.taskplanner.data.dao.TaskDao
import org.adaschool.taskplanner.storage.SharedPreferencesStorage
import org.adaschool.taskplanner.storage.Storage
import org.adaschool.taskplanner.utils.DATABASE_NAME
import org.adaschool.taskplanner.utils.SHARED_PREFERENCES_FILE_NAME
import javax.inject.Singleton

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideStorage(@ApplicationContext context: Context): Storage {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
        return SharedPreferencesStorage(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAnalyticsAdapter(): AnalyticsAdapter {
        return FlurryAnalyticsAdapter()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

}