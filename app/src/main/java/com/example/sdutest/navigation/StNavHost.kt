/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sdutest.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sdutest.feature.bookmarks.navigation.bookmarksScreen
import com.example.sdutest.feature.bookmarks.navigation.navigateToBookmarks
import com.example.sdutest.feature.main.navigation.SessionRoute
import com.example.sdutest.feature.main.navigation.mainScreen
import com.example.sdutest.feature.main.navigation.navigateSessionDetail
import com.example.sdutest.feature.setting.navigation.settingScreen


/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun NiaNavHost(
    navController: NavHostController,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = SessionRoute.route,
    onChangeDarkTheme: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
//        forYouScreen(onTopicClick = navController::navigateToTopic)
        bookmarksScreen(
            onTopicClick = {},
            onShowSnackbar = onShowSnackbar,
        )
        mainScreen(
            onBackClick = {},
            onSessionClick = {
                navController.navigateSessionDetail(it.enName)
            },
            onShowSnackbar = onShowSnackbar,
        )
        settingScreen(
            onChangeDarkTheme = onChangeDarkTheme
        )


//        searchScreen(
//            onBackClick = navController::popBackStack,
//            onInterestsClick = { appState.navigateToTopLevelDestination(INTERESTS) },
//            onTopicClick = navController::navigateToTopic,
//        )
//        interestsGraph(
//            onTopicClick = navController::navigateToTopic,
//            nestedGraphs = {
//                topicScreen(
//                    onBackClick = navController::popBackStack,
//                    onTopicClick = navController::navigateToTopic,
//                )
//            },
//        )
    }
}
