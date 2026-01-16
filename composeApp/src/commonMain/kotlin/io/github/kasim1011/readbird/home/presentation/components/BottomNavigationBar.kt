package io.github.kasim1011.readbird.home.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.kasim1011.readbird.home.domain.models.AppScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import readbird.composeapp.generated.resources.Res
import readbird.composeapp.generated.resources.menu_feeds
import readbird.composeapp.generated.resources.menu_settings
import readbird.composeapp.generated.resources.menu_subscriptions
import readbird.composeapp.generated.resources.rss_feed_24px
import readbird.composeapp.generated.resources.settings_24px
import readbird.composeapp.generated.resources.subscriptions_24px

data class BottomNavItem(
    val screen: AppScreen,
    val icon: DrawableResource, // Will be different per platform
    val label: StringResource
)

@Composable
fun BottomNavigationBar(
    currentScreen: AppScreen,
    onItemSelected: (AppScreen) -> Unit
) {
    val items = listOf(
        BottomNavItem(
            screen = AppScreen.FEEDS,
            icon = Res.drawable.rss_feed_24px,
            label = Res.string.menu_feeds
        ),
        BottomNavItem(
            screen = AppScreen.SUBSCRIPTIONS,
            icon = Res.drawable.subscriptions_24px,
            label = Res.string.menu_subscriptions
        ),
        BottomNavItem(
            screen = AppScreen.SETTINGS,
            icon = Res.drawable.settings_24px,
            label = Res.string.menu_settings
        )
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { item ->
            AddItem(
                item = item,
                currentScreen = currentScreen,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentScreen: AppScreen,
    onItemSelected: (AppScreen) -> Unit
) {
    NavigationBarItem(
        selected = currentScreen == item.screen,
        onClick = { onItemSelected(item.screen) },
        icon = {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = stringResource(item.label)
            )
        },
        label = { Text(stringResource(item.label)) },
        alwaysShowLabel = true
    )
}
