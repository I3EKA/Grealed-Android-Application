package com.example.grailed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grailed.ui.theme.GrailedTheme

@Composable
fun ProfileScreen(
    navController: NavController,
    initialTab: String
) {

    val parsedTab = when (initialTab.lowercase()) {
        "favourites" -> ProfileTab.FAVOURITES
        "saved" -> ProfileTab.SAVED
        "closet" -> ProfileTab.CLOSET
        else -> ProfileTab.SELLING
    }
    var selectedTab by remember { mutableStateOf(parsedTab) }

    GrailedTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp,end = 16.dp, bottom = 8.dp, top = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Аватар
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = "AnuarOralov",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "0 Transactions",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                            Icon(
                                imageVector = Icons.Default.Settings, // замените на свою иконку
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }

                    }

                    // Статистика и Edit Profile Button
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Reviews, Followers, Following
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileStat("0.0", "Reviews")
                    Spacer(modifier = Modifier.width(16.dp))
                    ProfileStat("0", "Following")
                    Spacer(modifier = Modifier.width(16.dp))
                    ProfileStat("0", "Followers")

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedButton(
                        onClick = { navController.navigate(Screen.EditProfile.route) },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text("EDIT PROFILE")
                    }
                }

                // Joined info
                Text(
                    text = "Joined in 2024  ·  Asia",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    modifier = Modifier.padding(start = 16.dp,end = 16.dp, top = 8.dp, bottom = 32.dp)
                )

                // Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    ProfileTabItem("SELLING", selectedTab == ProfileTab.SELLING) {
                        selectedTab = ProfileTab.SELLING
                    }
                    ProfileTabItem(
                        "FAVOURITES",
                        selectedTab == ProfileTab.FAVOURITES
                    ) { selectedTab = ProfileTab.FAVOURITES }
                    ProfileTabItem("SAVED", selectedTab == ProfileTab.SAVED) {
                        selectedTab = ProfileTab.SAVED
                    }
                    ProfileTabItem("CLOSET", selectedTab == ProfileTab.CLOSET) {
                        selectedTab = ProfileTab.CLOSET
                    }
                }

                // Content based on tab
                when (selectedTab) {
                    ProfileTab.SELLING -> EmptyState(
                        title = "You don't have any listings for sale.",
                        subtitle = "Go to the Sell tab to get started."
                    )

                    ProfileTab.FAVOURITES -> EmptyState(
                        title = "You don't have any favorite listings.",
                        subtitle = "Explore Grealed and favorite items to get notified if they drop in price."
                    )

                    ProfileTab.SAVED -> EmptyState(
                        title = "You haven't saved any listings yet.",
                        subtitle = "Explore Grealed and bookmark listings to save them privately."
                    )

                    ProfileTab.CLOSET -> EmptyState(
                        title = "You haven't made any purchases yet.",
                        subtitle = "Explore Grealed to purchase instantly or use the Offer button to negotiate a price within your budget."
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

enum class ProfileTab { SELLING, FAVOURITES, SAVED, CLOSET }

@Composable
fun RowScope.ProfileTabItem(text: String, selected: Boolean, onClick: () -> Unit) {
    val containerColor =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val contentColor =
        if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(1.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier
            .weight(1f)
            .height(40.dp)
            .align(Alignment.CenterVertically),
        shape = MaterialTheme.shapes.small,
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun EmptyState(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}
