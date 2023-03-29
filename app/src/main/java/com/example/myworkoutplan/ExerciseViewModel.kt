package com.example.myworkoutplan

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ExerciseViewModelc(private val repository :ExerciseRepositary):ViewModel() {


val asp  :LiveData<List<ExerciseLIst>> = repository.exerciseList.asLiveData()
    // this will transform flow data to live data

    fun insert(exerciselist :ExerciseLIst) {
        viewModelScope.launch {
            repository.insertExercise(exerciselist)
        }
    }


}
class WordViewModelFactory(private val repository: ExerciseRepositary) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModelc::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModelc(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
