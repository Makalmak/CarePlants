package com.example.careplants.data

import android.content.Context
import androidx.room.*


@Database(entities = [Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlantDatabase: RoomDatabase() {

    abstract fun plantDao(): PlantDAO

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    "plant_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}