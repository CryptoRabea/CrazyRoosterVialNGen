package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.AnalyticsScreen
import com.example.ui.screens.CreatorScreen
import com.example.ui.screens.FinancesScreen
import com.example.ui.screens.StrategyScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme(darkTheme = true) { // Force dark mode for a video editor aesthetic
        ViralCreatorApp()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViralCreatorApp() {
    val navController = rememberNavController()
    val onlineUsers = remember { (10000..15000).random() } 
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Viral Creator", fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color.Green)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                "$onlineUsers online",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            val items = listOf(
                Pair("creator", Icons.Default.AddCircleOutline),
                Pair("strategy", Icons.Default.DateRange),
                Pair("analytics", Icons.Default.Analytics),
                Pair("finances", Icons.Default.AttachMoney)
            )
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                items.forEach { (route, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = route) },
                        selected = currentRoute == route,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "creator",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("creator") { CreatorScreen() }
            composable("strategy") { StrategyScreen() }
            composable("analytics") { AnalyticsScreen() }
            composable("finances") { FinancesScreen() }
        }
    }
}

