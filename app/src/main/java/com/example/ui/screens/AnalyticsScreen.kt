package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Real-Time Analytics", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        // Retention Chart
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ShowChart, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(8.dp))
                    Text("Viewer Retention", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Faking a bar chart
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val heights = listOf(1f, 0.9f, 0.8f, 0.75f, 0.6f, 0.55f, 0.4f, 0.3f, 0.2f, 0.15f)
                    heights.forEach { fraction ->
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .fillMaxHeight(fraction)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = if (fraction > 0.5f) 1f else 0.5f))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("0s", style = MaterialTheme.typography.labelSmall)
                    Text("15s", style = MaterialTheme.typography.labelSmall)
                    Text("30s", style = MaterialTheme.typography.labelSmall)
                }
            }
        }

        // Suggestions
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Trending Audio Alert", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSecondaryContainer)
                    Text("3 new audios trending in your niche. Tap to use.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }

        // Team Collaboration
        Text("Team Workflow", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Group, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("2 Editors Active", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(progress = { 0.7f }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(4.dp))
                Text("Drafts syncing to Secure Cloud Storage...", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
