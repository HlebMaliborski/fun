package com.example.core_db_impl.`fun`

import androidx.room.Dao
import androidx.room.Query
import com.example.core_db_impl.dao.BaseDao

@Dao
interface DaoFun : BaseDao<EntityDbFun> {
    @Query("SELECT * FROM results")
    fun getAll(): List<EntityDbFun>

    @Query("SELECT * FROM results ORDER BY result DESC LIMIT 1")
    fun getBest(): EntityDbFun?
}