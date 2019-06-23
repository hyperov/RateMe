package com.nabil.rateme.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "movies", indices = [Index("name", unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var name: String,
    var image: Int,
    var rating: Float
)