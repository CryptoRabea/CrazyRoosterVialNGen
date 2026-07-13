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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
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
                    Icon(Icons.AutoMirrored.Filled.ShowChart, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
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

        // Trend Analysis Dashboard
        var selectedCategory by remember { mutableStateOf("Tech") }
        val categories = listOf("Tech", "Lifestyle", "Gaming")
        
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("AI Trend Categories", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Chart based on category
                val trendData = when (selectedCategory) {
                    "Tech" -> listOf("AI" to 0.9f, "Web3" to 0.6f, "Gadgets" to 0.4f, "Coding" to 0.8f)
                    "Lifestyle" -> listOf("Vlogs" to 0.8f, "Travel" to 0.7f, "Fitness" to 0.9f, "Food" to 0.5f)
                    "Gaming" -> listOf("E-Sports" to 0.95f, "Reviews" to 0.6f, "Let's Play" to 0.75f, "Lore" to 0.4f)
                    else -> emptyList()
                }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    trendData.forEach { (topic, score) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .fillMaxHeight(score)
                                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                    .background(MaterialTheme.colorScheme.tertiary)
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(topic, style = MaterialTheme.typography.labelSmall, maxLines = 1)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                
                Text("Trend Velocity vs. Audience Interest", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                
                val scatterData = when (selectedCategory) {
                    "Tech" -> listOf(Triple("AI", 0.85f, 0.9f), Triple("Web3", 0.3f, 0.4f), Triple("Gadgets", 0.6f, 0.5f), Triple("Coding", 0.7f, 0.8f))
                    "Lifestyle" -> listOf(Triple("Vlogs", 0.7f, 0.8f), Triple("Travel", 0.5f, 0.6f), Triple("Fitness", 0.9f, 0.85f), Triple("Food", 0.6f, 0.7f))
                    "Gaming" -> listOf(Triple("E-Sports", 0.9f, 0.95f), Triple("Reviews", 0.4f, 0.5f), Triple("Let's Play", 0.8f, 0.75f), Triple("Lore", 0.5f, 0.4f))
                    else -> emptyList()
                }

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    val maxWidthDp = maxWidth
                    val maxHeightDp = maxHeight
                    val dotColor = MaterialTheme.colorScheme.primary
                    
                    androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                        // Axes
                        drawLine(
                            color = androidx.compose.ui.graphics.Color.Gray.copy(alpha = 0.5f),
                            start = androidx.compose.ui.geometry.Offset(0f, size.height),
                            end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                            strokeWidth = 2f
                        )
                        drawLine(
                            color = androidx.compose.ui.graphics.Color.Gray.copy(alpha = 0.5f),
                            start = androidx.compose.ui.geometry.Offset(0f, 0f),
                            end = androidx.compose.ui.geometry.Offset(0f, size.height),
                            strokeWidth = 2f
                        )
                        
                        scatterData.forEach { (_, velocity, interest) ->
                            val x = velocity * size.width
                            val y = size.height - (interest * size.height)
                            drawCircle(
                                color = dotColor,
                                radius = 6.dp.toPx(),
                                center = androidx.compose.ui.geometry.Offset(x, y)
                            )
                        }
                    }
                    
                    scatterData.forEach { (name, velocity, interest) ->
                        Text(
                            text = name,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.offset(
                                x = (maxWidthDp * velocity) - 10.dp,
                                y = (maxHeightDp * (1f - interest)) - 20.dp
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Low Velocity", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("High Velocity", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        // Daily Email Digest
        var isDigestEnabled by remember { mutableStateOf(false) }
        
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(8.dp))
                        Text("Daily Email Digest", style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Receive a daily summary of the top 3 trending topics in '$selectedCategory'.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = isDigestEnabled,
                    onCheckedChange = { isDigestEnabled = it }
                )
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
