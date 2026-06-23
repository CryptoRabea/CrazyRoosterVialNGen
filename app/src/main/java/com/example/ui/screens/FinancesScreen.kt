package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun FinancesScreen() {
    val scrollState = rememberScrollState()
    var showReportDialog by remember { mutableStateOf(false) }
    var budgetLimit by remember { mutableFloatStateOf(2000f) }
    val projectedCost = 2100f
    
    if (showReportDialog) {
        ExpenseReportDialog(onDismiss = { showReportDialog = false })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Expense Reports & Projections", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        // Budget Settings
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Monthly Budget Limit: $${budgetLimit.roundToInt()}", style = MaterialTheme.typography.titleMedium)
                Slider(
                    value = budgetLimit,
                    onValueChange = { budgetLimit = it },
                    valueRange = 1000f..5000f,
                    steps = 39,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        // Notification Alert for Exceeded Budget
        if (projectedCost > budgetLimit) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Warning, contentDescription = "Warning", tint = MaterialTheme.colorScheme.error)
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text("Budget Alert!", fontWeight = FontWeight.Bold)
                        Text(
                            "Projected monthly expenses for video rendering and storage ($${projectedCost.roundToInt()}) exceed your predefined budget.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        // Snapshot
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard("Current Spend", "$1,240", Modifier.weight(1f), MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant)
            StatCard("Est. Revenue", "$4,850", Modifier.weight(1f), MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.onPrimaryContainer)
        }

        // Projection
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Analytics, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(8.dp))
                    Text("Forecasted Costs", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                // Timeline
                val formatter = DateTimeFormatter.ofPattern("MMM dd")
                val now = LocalDate.now()
                
                ProjectionRow("Today", now.format(formatter), "$1,240")
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                ProjectionRow("+7 Days", now.plusDays(7).format(formatter), "$1,450", true)
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                ProjectionRow("End of Month", now.plusDays(30).format(formatter), "$${projectedCost.roundToInt()}", true)
            }
        }

        // Action Buttons
        Text("Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { showReportDialog = true }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Receipt, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Generate Expense Report")
                }
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.AttachMoney, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Clarify Discrepancies")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ExpenseReportDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Receipt, contentDescription = null, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(8.dp))
                Text("Monthly Financial Summary")
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Below is the detailed breakdown of expected monthly costs.", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                
                CostItemRow("AI Trend Analysis", "$800.00")
                CostItemRow("Cloud Asset Storage", "$450.00")
                CostItemRow("Video Rendering (Compute)", "$850.00")
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total Projected", fontWeight = FontWeight.Bold)
                    Text("$2,100.00", fontWeight = FontWeight.Bold)
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text("Download PDF")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun CostItemRow(label: String, cost: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(cost, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun StatCard(label: String, value: String, modifier: Modifier = Modifier, containerColor: Color, contentColor: Color) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = containerColor, contentColor = contentColor)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProjectionRow(label: String, date: String, cost: String, isFuture: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Text(date, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(
            cost, 
            style = MaterialTheme.typography.bodyLarge, 
            fontWeight = FontWeight.Bold,
            color = if (isFuture) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}
