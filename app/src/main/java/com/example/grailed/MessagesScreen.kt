package com.example.grailed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MessagesScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(MessageTab.BUY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Messages",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            TabItem(
                text = "BUY",
                selected = selectedTab == MessageTab.BUY,
                onClick = { selectedTab = MessageTab.BUY }
            )
            Spacer(modifier = Modifier.width(2.dp))
            TabItem(
                text = "SELL",
                selected = selectedTab == MessageTab.SELL,
                onClick = { selectedTab = MessageTab.SELL }
            )
        }

        when (selectedTab) {
            MessageTab.BUY -> BuyMessagesContent()
            MessageTab.SELL -> SellMessagesContent()
        }
    }
}

@Composable
fun RowScope.TabItem(text: String, selected: Boolean, onClick: () -> Unit) {
    // Choose colors based on selection and theme
    val containerColor =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val contentColor =
        if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier
            .weight(1f)
            .height(40.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(text = text)
    }
}

@Composable
fun BuyMessagesContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You have no messages yet.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "The Buy Inbox is for any communication about items you want to buy. " +
                    "Make a Purchase, Send an Offer, or Ask a Question " +
                    "to start a conversation with a Seller.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
        Button(
            onClick = { /*TODO: refresh logic*/ },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "Refresh", modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun SellMessagesContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You have no messages yet.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "The Sell Inbox is for any communication regarding items you are selling. " +
                    "Give potential buyers more information, Accept Offers, and send Counter Offers. " +
                    "Visit the Sell Tab to start posting items!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        Button(
            onClick = { /*TODO: refresh logic*/ },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "Refresh", modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

enum class MessageTab { BUY, SELL }

