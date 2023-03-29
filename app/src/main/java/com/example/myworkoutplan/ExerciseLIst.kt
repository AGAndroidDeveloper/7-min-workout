package com.example.myworkoutplan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Exercise-list")
     data class ExerciseLIst(
    @PrimaryKey(autoGenerate = true)
   val id :Int = 0,
    @ColumnInfo
    val date :String

) {


}
