package com.example.core_db_impl.`fun`

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class EntityDbFun(@PrimaryKey(autoGenerate = true) val id: Int = 0, val result: Int)