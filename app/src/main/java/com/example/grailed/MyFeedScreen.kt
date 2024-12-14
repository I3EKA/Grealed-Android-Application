package com.example.grailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MyFeedScreen(navController: NavController) {
    // Прокручиваемый контент
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Верхняя панель
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO: Drawer open*/ }) {
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

        // Первый горизонтальный список предметов (как на скриншоте: 2 столбца в ряду)
        // Для наглядности используем LazyVerticalGrid с 2 столбцами, чтобы показать списки товаров.
        // В реальности можно комбинировать горизонтальные и вертикальные списки, но предоставленные скриншоты
        // показывают в основном вертикальный список в несколько столбцов с разными товарами.

        // Ниже эмуляция "ленты" из товаров. Предположим, что данные приходят откуда-то извне:
        val items = (1..10).map { i ->
            FeedItemData(
                imageUrl = "", // заменить на реальную картинку
                brand = "Acne Studios Casual...",
                size = "32",
                title = "32",
                price = "55$",
                daysAgo = "1 day ago",
                recommendedText = "Recommended for you"
            )
        }

        // Можно разбить на секции. Допустим, первая секция - два предмета в ряд (горизонтальный скролл).
        SectionOfItems("Arc'Teryx Sweaters Knit...", items.subList(0, 8),navController)

        // При необходимости можно добавить ещё секций как на скриншоте.
        // Если на скриншоте просто бесконечная лента, достаточно одного грид-листа.
    }
}

@Composable
fun SectionOfItems(sectionTitle: String, items: List<FeedItemData>,navController:NavController) {
    // Заголовок секции можно не ставить, если по скриншоту его нет, или стилизовать иначе.
    // По скриншотам особо нет явного заголовка, просто лента товаров. Но добавим для наглядности.
    Spacer(modifier = Modifier.height(16.dp))

    // Сетка с 2 столбцами, если нужно строго по два товара в ряд
    // Если нужно сделать горизонтальный скролл: заменить на Row с horizontalScroll.
    val chunkedSchedules = items.chunked(2)
    chunkedSchedules.forEach { pair ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            pair.forEach { it ->
                FeedItemCard(it,navController)
            }
            if (pair.size == 1) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun RowScope.FeedItemCard(item: FeedItemData,navController: NavController) {
    Column(
        modifier = Modifier.weight(1f).clickable { navController.navigate(Screen.Detail.route) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.a1),
            null,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray) // заменить на Image(...)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Days ago
        Column(Modifier.padding(horizontal = 8.dp)) {
            Text(
                text = item.daysAgo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Row {
                Text(
                    text = item.brand,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.size,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.price,
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
        }
    }
}

// Данные о товаре (фейковые)
data class FeedItemData(
    val imageUrl: String,
    val brand: String,
    val size: String,
    val title: String,
    val price: String,
    val daysAgo: String,
    val recommendedText: String
)
