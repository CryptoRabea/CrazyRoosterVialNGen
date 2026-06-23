package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmAdd
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StrategyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
            Spacer(Modifier.width(8.dp))
            Text("Content Strategy", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
        
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    Spacer(Modifier.width(8.dp))
                    Text("30-Day AI Strategy", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(Modifier.height(4.dp))
                Text("Your content calendar is optimized for peak engagement. AI agents have mapped out trends for the next 30 days.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Spacer(Modifier.height(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text("Regenerate Strategy")
                }
            }
        }
        
        Text("Upcoming Schedule (with Alarms)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(30) { index ->
                StrategyDayCard(day = index + 1)
            }
        }
    }
}

@Composable
fun StrategyDayCard(day: Int) {
    val isToday = day == 1
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isToday) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Day $day", fontWeight = FontWeight.Bold, color = if (isToday) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant)
                Text(if (day % 3 == 0) "Trend: 'Point of View' Audio" else "Hook: 'You won't believe this...'", style = MaterialTheme.typography.bodySmall)
                Text("Post at 5:30 PM", style = MaterialTheme.typography.labelMedium)
            }
            IconButton(onClick = { /* Set alarm */ }) {
                Icon(
                    if (isToday) Icons.Default.CheckCircle else Icons.Default.AlarmAdd,
                    contentDescription = "Set posting alarm",
                    tint = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
