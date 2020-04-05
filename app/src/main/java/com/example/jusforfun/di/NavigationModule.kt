package com.example.jusforfun.di

import com.example.core_navigation_api.FunListNavigation
import com.example.core_navigation_api.TestNavigation
import com.example.jusforfun.navigation.Navigator
import org.koin.dsl.module

val navigationModule = module {
    /*  single { (navigation: NavController) -> Navigator(navigation) } binds arrayOf(
          FunListNavigation::class,
          TestNavigation::class
      )*/
    single<TestNavigation> { get<Navigator>() }
    single<FunListNavigation> { get<Navigator>() }
}