package com.example.myworkoutplan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database( entities = [ExerciseLIst::class], version = 3)
abstract class ExerciseListDataBase :RoomDatabase(){
    abstract fun eDao() :ExerciseDao

    //If your app runs in a single process, you should follow the singleton design pattern when
// instantiating an AppDatabase object. Each RoomDatabase instance is fairly expensive, and you
// rarely need access to multiple instances within a single process.
    //
    //If your app runs in multiple processes, include enableMultiInstanceInvalidation() in your
// database builder invocation. That way, when you have an instance of AppDatabase in each process,
// you can invalidate the shared database file in one process, and this invalidation automatically
// propagates to the instances of AppDatabase within other processes.



    companion object{
                 @Volatile
                 var INSTANCE :ExerciseListDataBase? = null      // singleton pattern

         fun dataBaseObj( context: Context,

         ) :ExerciseListDataBase{
                  synchronized(this){

                      if (INSTANCE==null) {
                          val db = Room.databaseBuilder(context.applicationContext,
                              ExerciseListDataBase::class.java,"Exercise-database").fallbackToDestructiveMigration()
//                              .addCallback(WordDatabaseCallback(scope))
                              .build()
                          INSTANCE = db
                      } }
return INSTANCE!!

         }

    }




}