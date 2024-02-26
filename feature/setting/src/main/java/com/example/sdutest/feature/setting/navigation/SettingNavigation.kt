package com.example.sdutest.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.sdutest.feature.setting.SettingScreen

fun NavController.navigateToSetting(navOptions: NavOptions) {
    navigate(SettingRoute.route, navOptions)
}

fun NavGraphBuilder.settingScreen(
    onChangeDarkTheme: (Boolean) -> Unit
) {
    composable(route = SettingRoute.route) {
        SettingScreen(onChangeDarkTheme)
    }
}

object SettingRoute {
    const val route = "setting"
}
