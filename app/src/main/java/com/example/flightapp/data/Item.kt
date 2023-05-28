package com.example.flightapp.data

import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name = "iata_code")
    val iataCode: String,
    @NonNull
    val name: String,
    @NonNull
    val passengers: Int
)

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name = "departure_code")
    val departureCode: String,
    @NonNull
    @ColumnInfo(name = "destination_code")
    val destinationCode: String
)

@Entity(tableName = "selected", primaryKeys = ["user_id", "favorite_code"])
data class Selected(
    @NonNull
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @NonNull
    @ColumnInfo(name = "favorite_code")
    val favoriteCode: Int
)

data class Route(
    @NonNull
    @ColumnInfo(name = "favorite_code")
    val favoriteCode: Int,
    @NonNull
    @ColumnInfo(name = "departure_code")
    val departureCode: String,
    @NonNull
    @ColumnInfo(name = "departure_name")
    val departureName: String,
    @NonNull
    @ColumnInfo(name = "destination_code")
    val destinationCode: String,
    @NonNull
    @ColumnInfo(name = "destination_name")
    val destinationName: String,
    @ColumnInfo(name = "user_id")
    val userId: String? = null

)