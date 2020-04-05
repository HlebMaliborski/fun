package com.example.core_db_impl.di

import androidx.room.Room
import com.example.core_db_api.`fun`.DataSourceDbFunApi
import com.example.core_db_impl.`fun`.*
import com.example.core_db_impl.database.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "fun_database").build() }
    single { get<AppDatabase>().funDao() }
    single<EntityFunMapper> { EntityFunMapperImpl() }
    single<EntityFunMapperList> { EntityFunMapperListImpl() }
    single<DataSourceDbFunApi> {
        DataSourceDbFunImpl(
            dao = get(),
            entityFunMapper = get(),
            entityFunMapperList = get()
        )
    }
}