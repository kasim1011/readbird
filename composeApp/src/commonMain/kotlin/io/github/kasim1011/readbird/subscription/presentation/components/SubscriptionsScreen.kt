package io.github.kasim1011.readbird.subscription.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kasim1011.readbird.home.domain.models.Subscription
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import readbird.composeapp.generated.resources.Res
import readbird.composeapp.generated.resources.add
import readbird.composeapp.generated.resources.add_24px
import readbird.composeapp.generated.resources.add_account
import readbird.composeapp.generated.resources.category_optional
import readbird.composeapp.generated.resources.cta_add
import readbird.composeapp.generated.resources.cta_cancel
import readbird.composeapp.generated.resources.cta_manually_or_import
import readbird.composeapp.generated.resources.ic_twitter
import readbird.composeapp.generated.resources.import_from_twitter
import readbird.composeapp.generated.resources.menu_subscriptions
import readbird.composeapp.generated.resources.no_subscriptions
import readbird.composeapp.generated.resources.twitter
import readbird.composeapp.generated.resources.username_without_at

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionsScreen(
    subscriptions: List<Subscription>,
    onAddSubscription: () -> Unit,
    onRemoveSubscription: (String) -> Unit,
    onImportFromTwitter: () -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.menu_subscriptions)) },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(
                            painter = painterResource(Res.drawable.add_24px),
                            contentDescription = stringResource(Res.string.add)
                        )
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onImportFromTwitter,
                icon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_twitter),
                        contentDescription = stringResource(Res.string.twitter)
                    )
                },
                text = { Text(stringResource(Res.string.import_from_twitter)) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (subscriptions.isEmpty()) {
                EmptySubscriptionsState()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(subscriptions, key = { it.id }) { subscription ->
                        SubscriptionItem(
                            subscription = subscription,
                            onRemove = { onRemoveSubscription(subscription.id) }
                        )
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddSubscriptionDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { username, category ->
                // Handle adding subscription
                showAddDialog = false
            }
        )
    }
}

@Composable
fun AddSubscriptionDialog(
    onDismiss: () -> Unit,
    onAdd: (username: String, category: String?) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(Res.string.add_account)) },
        text = {
            Column {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(Res.string.username_without_at)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = category ?: "",
                    onValueChange = { category = if (it.isBlank()) null else it },
                    label = { Text(stringResource(Res.string.category_optional)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onAdd(username, category) },
                enabled = username.isNotBlank()
            ) {
                Text(stringResource(Res.string.cta_add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(Res.string.cta_cancel))
            }
        }
    )
}

@Composable
fun EmptySubscriptionsState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.no_subscriptions),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(Res.string.cta_manually_or_import),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
