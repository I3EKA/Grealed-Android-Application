package com.example.grailed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

sealed class SellTab(val title: String) {
    object Home : SellTab("HOME")
    object ForSale : SellTab("FOR SALE")
    object Sold : SellTab("SOLD")
    object Drafts : SellTab("DRAFTS")
}

@Composable
fun SellScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf<SellTab>(SellTab.Home) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Sell",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, bottom = 16.dp)
        )

        // Вкладки
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp,end = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.surface)


        ) {
            SellTabItem(
                text = "HOME",
                selected = selectedTab == SellTab.Home,
                onClick = { selectedTab = SellTab.Home }
            )
            SellTabItem(
                text = "FOR SALE",
                selected = selectedTab == SellTab.ForSale,
                onClick = { selectedTab = SellTab.ForSale }
            )
            SellTabItem(
                text = "SOLD",
                selected = selectedTab == SellTab.Sold,
                onClick = { selectedTab = SellTab.Sold }
            )
            SellTabItem(
                text = "DRAFTS",
                selected = selectedTab == SellTab.Drafts,
                onClick = { selectedTab = SellTab.Drafts }
            )
        }

        when (selectedTab) {
            SellTab.Home -> SellHomeContent()
            SellTab.ForSale -> SellEmptyContent(
                title = "You have not added any Items yet",
                description = "Visit the Sell Tab to post your items for free and start selling instantly. " +
                        "Sell with confidence, all sales are protected by both Paypal and Grealed Protection."
            )

            SellTab.Sold -> SellEmptyContent(
                title = "You have not sold any items yet",
                description = "Once you've posted an item for sale, drum up extra interest for your listing by " +
                        "bumping it to the top of the feed every seven days. You can also use the 'Drop Price by 10%' " +
                        "button to bump it to the top at any time"
            )

            SellTab.Drafts -> SellEmptyContent(
                title = "You do not have any listing drafts yet",
                description = "Visit the Sell Tab to post your items for free and start selling instantly. " +
                        "Sell with confidence, all sales are protected by both Paypal and Grealed Protection."
            )
        }
    }
}

@Composable
fun RowScope.SellTabItem(text: String, selected: Boolean, onClick: () -> Unit) {
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
fun SellHomeContent() {
    // Вертикальная прокрутка, так как возможно много уведомлений и инструментов
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            // Notifications
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            NotificationCard(
                title = "Want to sell on Grealed?",
                subtitle = "We need to know where to send your money.",
                buttonText = "CONTINUE",
                primary = true
            )
            NotificationCard(
                title = "Connect PayPal to sell items",
                subtitle = "",
                buttonText = "ADD"
            )
            NotificationCard(
                title = "Complete Your Profile",
                subtitle = "Add an image and a username to increase your chances to sell.",
                buttonText = "ADD"
            )

            // Tools
            Text(
                text = "Tools",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            ToolItem("Browse Sold Listings")
            ToolItem("Vacation Mode")
            ToolItem("Shipping Labels")

            Spacer(modifier = Modifier.height(100.dp)) // Отступ для кнопки внизу
        }

        // Кнопка ADD A LISTING внизу
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0000FF),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text("ADD A LISTING")
            }
        }
    }
}

@Composable
fun NotificationCard(title: String, subtitle: String, buttonText: String,primary: Boolean=false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            // Красная точка
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.CenterVertically)
                    .background(Color.Red, shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(1f))

            if(primary){
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0000FF),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(buttonText)
                }
            }
            else {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    border = BorderStroke(1.dp, Color(0xFF0000FF)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = buttonText, color = Color(0xFF0000FF))
                }
            }
        }
        if (subtitle.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun ToolItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /*TODO*/ }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Text(
            ">",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)
}

@Composable
fun SellEmptyContent(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Замените R.drawable.placeholder на реальный ресурс
        Image(
            painter = painterResource(id = R.drawable.place),
            contentDescription = "Placeholder",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 4.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}