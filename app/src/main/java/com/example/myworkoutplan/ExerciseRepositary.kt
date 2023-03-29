package com.example.myworkoutplan

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ExerciseRepositary(private val mdao :ExerciseDao) {


    val exerciseList : Flow<List<ExerciseLIst>> = mdao.fetchAllExerciseList()



    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
  @WorkerThread
    suspend fun insertExercise(exercise12 :ExerciseLIst) {
        mdao.insertExercise(exercise12)


    }

}