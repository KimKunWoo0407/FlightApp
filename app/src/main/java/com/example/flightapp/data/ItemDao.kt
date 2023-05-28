package com.example.flightapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("Select * from airport where name like :name or iata_code like :name")
    fun getAirport(name: String): Flow<List<Airport>>

    @Query("Select * from airport where iata_code like :code")
    fun getAirportsWithCode(code: String): Flow<List<Airport>>

    @Query("select departure_code, destination_code, a.name as departure_name, b.name as destination_name, favorite.id as favorite_code, s.user_id " +
            "from favorite inner join airport a " +
            "on departure_code =a.iata_code " +
            "inner join airport b on destination_code = b.iata_code " +
            "left outer join selected s on favorite.id = s.favorite_code " +
            "where departure_code = :code")
    fun getRouteWithCode(code: String): Flow<List<Route>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun bookMark(bookmark: Selected)

    @Delete
    suspend fun delete(bookmark: Selected)

}