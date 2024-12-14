package com.example.grailed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DiscoverScreen(navController: NavController) {
    // Скроллим весь контент
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Верхний AppBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO: Drawer?*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "GREALED",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { navController.navigate(Screen.Search1.route) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }

            IconButton(onClick = { navController.navigate("profile?tab=favourites") }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorites",
                    tint = Color.Black
                )
            }
        }

        // Большой баннер сверху
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray) // Здесь поставьте Image с painterResource(...) для реального баннера
        ) {
            // Текст поверх изображения
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Holiday 2024",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
                Text(
                    text = "The Holiday Edit",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Trending Searches
        SectionTitle("Trending Searches")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TagButton("SHOP ALL")
            TagButton("BALENCIAGA")
            TagButton("SUPREME")
            TagButton("GCS24")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recommended for You
        SectionHeaderWithAction(
            title = "Recommended for You",
            actionText = "SEE ALL",
            onActionClick = { /*TODO: Show all recommended*/ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Горизонтальный список товаров
        HorizontalItemsList(items = listOf("Item1", "Item2", "Item3", "Item4")) { itemName ->
            RecommendedItemCard(
                imageName = itemName,
                brand = "Acne Studios",
                size = "S",
                title = "Acne Studios 1981m C...",
                price = "$725",
                daysAgo = "1 day ago"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Designers For You
        SectionHeaderWithAction(
            title = "Designers For You",
            actionText = "SEE ALL",
            onActionClick = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Горизонтальный список дизайнеров
        HorizontalItemsList(items = listOf("Nike", "Supreme", "Gosha", "Gucci")) { designerName ->
            DesignerCard(
                designerName = designerName,
                listingsCount = "404 972 Listings"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun SectionHeaderWithAction(title: String, actionText: String, onActionClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        TextButton(onClick = onActionClick, shape = MaterialTheme.shapes.small,) {
            Text(actionText, color = Color.Blue)
        }
    }
}

@Composable
fun HorizontalItemsList(items: List<String>, itemContent: @Composable (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            itemContent(item)
        }
    }
}

@Composable
fun RecommendedItemCard(
    imageName: String,
    brand: String,
    size: String,
    title: String,
    price: String,
    daysAgo: String
) {
    Column(
        modifier = Modifier
            .width(140.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.LightGray) // Замените на Image(...)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = daysAgo,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Row{
            Text(
                text = brand,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = size,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = price,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Like",
                tint = Color.Black
            )
        }
        Text(
            text = "See Similar",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Blue
        )
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun DesignerCard(designerName: String, listingsCount: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .width(140.dp)
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),// Внутренние отступы карточки
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray) // Замените на Image(...) при необходимости
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = designerName,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = listingsCount,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Follow action */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("FOLLOW")
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun TagButton(text: String) {
    OutlinedButton(
        onClick = {},
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black),
        shape = MaterialTheme.shapes.small,
    ) {
        Text("$text ↗")
    }
}
