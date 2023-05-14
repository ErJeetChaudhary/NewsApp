package com.example.jokeapp.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by Jitendra on 14/05/23.
 **/
@Parcelize
@Entity(tableName = "table_joke")
data class Joke(
    @field:Json(name = "joke")
    @ColumnInfo(name = "joke")
    val joke: String
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @field:Json(name = "id")
    @ColumnInfo(name = "id")
    var id: Long = 0

}