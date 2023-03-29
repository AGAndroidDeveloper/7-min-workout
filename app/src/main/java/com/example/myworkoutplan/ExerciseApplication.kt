package com.example.myworkoutplan

import android.app.Application

class ExerciseApplication :Application() {

    val database by lazy {
        ExerciseListDataBase.dataBaseObj(this)

    }

}