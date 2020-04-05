package com.example.core_db_impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_db_impl.`fun`.DaoFun
import com.example.core_db_impl.`fun`.EntityDbFun

@Database(entities = [EntityDbFun::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun funDao(): DaoFun
}