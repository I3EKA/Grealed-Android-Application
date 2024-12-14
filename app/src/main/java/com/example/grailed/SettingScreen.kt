package com.example.grailed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grailed.ui.theme.GrailedTheme

@Composable
fun SettingsScreen(navController: NavController) {
    GrailedTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp,end= 16.dp, bottom = 8.dp, top = 16.dp)
                ) {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    // Buying Section
                    SettingsSection("Buying")
                    SettingsItem(title = "Shopping Preference", value = "Menswear") { /*TODO*/ }
                    SettingsItem(title = "My Sizes", value = "Add +") { /*TODO*/ }
                    SettingsItem(title = "Addresses") { /*TODO*/ }

                    // Selling Section
                    SettingsSection("Selling")
                    SettingsItem(title = "Browse Sold Listings") { /*TODO*/ }
                    SettingsItem(title = "Vacation Mode") { /*TODO*/ }
                    SettingsItem(title = "Shipping Labels", value = "New") { /*TODO*/ }

                    // Settings Section
                    SettingsSection("Settings")
                    SettingsItem(title = "Account") { /*TODO*/ }
                    SettingsItem(title = "Profile Privacy") { /*TODO*/ }
                    SettingsItem(title = "Payments") { /*TODO*/ }
                    SettingsItem(title = "Notifications") { /*TODO*/ }

                    // Help Section
                    SettingsSection("Help")
                    SettingsItem(title = "Authenticity and Protection") { /*TODO*/ }
                    SettingsItem(title = "Accessibility Statement") { /*TODO*/ }
                    SettingsItem(title = "Contact Support") { /*TODO*/ }
                    SettingsItem(title = "How to Sell Guide") { /*TODO*/ }
                    SettingsItem(title = "About") { /*TODO*/ }

                    // Sign Out
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Sign Out",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .padding(start = 16.dp,end =16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun SettingsItem(title: String, value: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (value != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(value, style = MaterialTheme.typography.bodySmall, color = Color.Blue)
        }
        Spacer(Modifier.weight(1f))
        Text(
            ">",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)
}
