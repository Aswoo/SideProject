package com.example.sdutest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sdutest.navigation.NiaNavHost
import com.example.sdutest.navigation.rememberMainNavigator
import com.example.sdutest.ui.MainTab
import com.example.sdutest.ui.StBottomBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val snackbarHostState = remember { SnackbarHostState() }
            val navigator = rememberMainNavigator(navController = rememberNavController())
            Scaffold(
                content = { padding ->
                    NiaNavHost(navController = navigator.navController, onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short,
                        ) == SnackbarResult.ActionPerformed
                    }, modifier = Modifier.padding(padding))
                }, bottomBar = {
                    StBottomBar(
                        visible = navigator.shouldShowBottomBar(),
                        tabs = MainTab.values().toList(),
                        currentTab = navigator.currentTab,
                        onTabSelected = { navigator.navigate(it) }
                    )
                }
            )

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}