package io.github.kasim1011.readbird.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import readbird.composeapp.generated.resources.Res
import readbird.composeapp.generated.resources.menu_settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isConnected: Boolean,
    username: String?,
    autoRefreshEnabled: Boolean,
    wifiOnlySync: Boolean,
    cacheSize: String,
    appVersion: String,
    onDisconnect: () -> Unit,
    onAutoRefreshChange: (Boolean) -> Unit,
    onWifiOnlyChange: (Boolean) -> Unit,
    onClearCache: () -> Unit,
    onCheckUpdates: () -> Unit,
    onExportSubscriptions: () -> Unit,
    onGitHubClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.menu_settings)) },
                modifier = Modifier.statusBarsPadding()
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Account Management
            SectionHeader("ACCOUNT MANAGEMENT")
            SettingCard {
                Column {
                    Text(
                        text = "Twitter Account",
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (isConnected && username != null) {
                        Text(
                            text = "Connected as @$username",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = onDisconnect,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Disconnect")
                        }
                    } else {
                        Text(
                            text = "Not connected",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = { /* Connect */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Connect Twitter")
                        }
                    }
                }
            }

            // Data & Sync
            SectionHeader("DATA & SYNC")
            SettingCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Auto-refresh",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Every 15 minutes",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    Switch(
                        checked = autoRefreshEnabled,
                        onCheckedChange = onAutoRefreshChange
                    )
                }
            }

            SettingCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "WiFi-only sync",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Save mobile data",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    Switch(
                        checked = wifiOnlySync,
                        onCheckedChange = onWifiOnlyChange
                    )
                }
            }

            SettingCard(
                onClick = onClearCache
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Clear Cache",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = cacheSize,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // App
            SectionHeader("APP")
            SettingCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Version",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = appVersion,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            SettingCard(
                onClick = onCheckUpdates
            ) {
                Text(
                    text = "Check for Updates",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            SettingCard(
                onClick = onGitHubClick
            ) {
                Column {
                    Text(
                        text = "GitHub Repository",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "github.com/username/app",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Export Button
            OutlinedButton(
                onClick = onExportSubscriptions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Export Subscriptions")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
    )
}

@Composable
fun SettingCard(
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick ?: {}
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}
