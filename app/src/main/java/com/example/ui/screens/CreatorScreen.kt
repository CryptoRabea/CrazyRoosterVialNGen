package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CreatorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Video Preview Canvas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Video",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text("Select an asset or start recording")
            }
            
            // Floating tools on video
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))) {
                    Icon(Icons.Default.Mic, contentDescription = "Trending Audio")
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = "AI Effects")
                }
            }
        }

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.onPrimaryContainer)
            ) {
                Icon(Icons.Default.UploadFile, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Import")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer)
            ) {
                Icon(Icons.Default.Videocam, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Record")
            }
        }

        Text("Smart Tools", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        // Tools Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(200.dp),
            userScrollEnabled = false
        ) {
            item { ToolCard("AI Trend Detection", Icons.Default.TrendingUp) }
            item { ToolCard("Auto Captions", Icons.Default.Subtitles) }
            item { ToolCard("Smart Edit", Icons.Default.AutoFixHigh) }
            item { ToolCard("Brand Templates", Icons.Default.Style) }
        }

        Spacer(modifier = Modifier.weight(1f))

        // AI Agent Clip Generation
        Text("AI Agent Studio", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = MaterialTheme.colorScheme.onTertiaryContainer)
                    Spacer(Modifier.width(8.dp))
                    Text("Autonomous Creation", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onTertiaryContainer)
                }
                Text("Let AI agents generate a high-retention 30sec viral clip tailored to trending formats, directly from your raw assets.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onTertiaryContainer)
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Generate 30s Viral Clip")
                }
            }
        }

        // Export Row
        Text("One-Click Export", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("TikTok Reach")
            }
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.VideoLibrary, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("IG Reels")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ToolCard(title: String, icon: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
