package com.example.jusforfun.navigation

import androidx.navigation.NavController
import com.example.core_navigation_api.FunListNavigation
import com.example.core_navigation_api.TestNavigation

class Navigator(private val navController: NavController) : FunListNavigation, TestNavigation {
    override fun open() {

    }

    override fun openTest() {

    }
}