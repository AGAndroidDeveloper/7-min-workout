package com.example.myworkoutplan

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {
    @Insert
    suspend fun insertExercise(exercise :ExerciseLIst)

    @Query("SELECT * FROM 'Exercise-list'")
    fun fetchAllExerciseList() :Flow<List<ExerciseLIst>>
     //A Flow is an async sequence of values
    //Flow produces values one at a time (instead of all at once) that can generate values from
     // async operations like network requests, database calls, or other async code. It supports
     // coroutines throughout its API, so you can transform a flow using coroutines as well!

    @Delete
    suspend fun deleteExercise(exercise :ExerciseLIst)

    @Update
    suspend fun updateExercise(exercise :ExerciseLIst)

   @Query("DELETE FROM 'Exercise-list'")
    suspend fun deleteAll()



}