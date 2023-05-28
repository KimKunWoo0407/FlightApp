package com.example.flightapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class, Selected::class], version = 1)
abstract class FlightDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile
        private var Instance: FlightDatabase? = null
        fun getDatabase(context: Context): FlightDatabase{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, FlightDatabase::class.java, "app_database")
                    .createFromAsset("database/flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
