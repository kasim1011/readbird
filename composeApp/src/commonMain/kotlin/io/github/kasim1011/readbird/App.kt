package io.github.kasim1011.readbird

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kasim1011.readbird.core.theme.AppTheme
import io.github.kasim1011.readbird.feed.presentation.components.FeedsScreen
import io.github.kasim1011.readbird.home.domain.models.AppScreen
import io.github.kasim1011.readbird.home.domain.models.AppState
import io.github.kasim1011.readbird.home.presentation.components.BottomNavigationBar
import io.github.kasim1011.readbird.settings.presentation.components.SettingsScreen
import io.github.kasim1011.readbird.subscription.presentation.components.SubscriptionsScreen

@Composable
@Preview
fun App() {
    var appState by remember { mutableStateOf(AppState()) }

    AppTheme {
        // Main content with bottom navigation
        Column(modifier = Modifier.fillMaxSize()) {
            // Current screen content
            Box(modifier = Modifier.weight(1f)) {
                when (appState.currentScreen) {
                    AppScreen.FEEDS -> FeedsScreen(
                        tweets = appState.tweets,
                        isLoading = appState.isLoading,
                        onRefresh = { /* Refresh logic */ },
                        onTweetClick = { /* Handle tweet click */ }
                    )

                    AppScreen.SUBSCRIPTIONS -> SubscriptionsScreen(
                        subscriptions = appState.subscriptions,
                        onAddSubscription = { /* Show add dialog */ },
                        onRemoveSubscription = { id ->
                            appState = appState.copy(
                                subscriptions = appState.subscriptions.filter { it.id != id }
                            )
                        },
                        onImportFromTwitter = { /* Import logic */ }
                    )

                    AppScreen.SETTINGS -> SettingsScreen(
                        isConnected = true,
                        username = "username",
                        autoRefreshEnabled = true,
                        wifiOnlySync = false,
                        cacheSize = "245 MB used",
                        appVersion = "1.0.0",
                        onDisconnect = { /* Disconnect logic */ },
                        onAutoRefreshChange = { enabled ->
                            // Update setting
                        },
                        onWifiOnlyChange = { enabled ->
                            // Update setting
                        },
                        onClearCache = { /* Clear cache logic */ },
                        onCheckUpdates = { /* Check updates logic */ },
                        onExportSubscriptions = { /* Export logic */ },
                        onGitHubClick = { /* Open GitHub */ }
                    )
                }
            }

            // Bottom Navigation
            BottomNavigationBar(
                currentScreen = appState.currentScreen,
                onItemSelected = { screen ->
                    appState = appState.copy(currentScreen = screen)
                }
            )
        }
    }
}
