package com.example.grailed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController) {
    val images = listOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
        R.drawable.a4,
        R.drawable.a5,
    )

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            // Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        },
        bottomBar = {
            // Action Buttons
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        shape = MaterialTheme.shapes.small,
                        onClick = { /* Offer action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("OFFER $55")
                    }
                    Button(
                        shape = MaterialTheme.shapes.small,
                        onClick = { /* Buy action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("BUY NOW")
                    }

                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // Image Pager
            val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = "Product Image $page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                // Page indicators
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    repeat(images.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (pagerState.currentPage == index) Color.Black else Color.Gray)
                                .padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Acne Studios",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.baseline_bookmark_border_24),
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )

            }

            Text(
                "Acne Studios Ishir PSS18 Pants Trousers",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Size: Mens US 32 / 48",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall, color = Color.Gray
            )
            Spacer(Modifier.height(16.dp))
            // Price Section
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "$55",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "+ $15 Shipping: Europe to Asia",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Measurements Section
            Text(
                text = "Measurements",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Measurements taken by seller with item in hand. Measured with garment laid flat.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(R.drawable.a8),
                contentDescription = null,
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Seller Description Section
            SellerDescription()

            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Tags Section
            TagsSection()

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Posted in Grealed ",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = "1 day ago",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }
            Spacer(Modifier.height(2.dp))
            Row {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Listing ID ",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = "7777777",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            ToolItem("Report Listing", R.drawable.rep)
            ToolItem("Price Comparison", R.drawable.price1)
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


@Composable
fun ToolItem(title: String, drawable: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /*TODO*/ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(drawable),
            contentDescription = "Menu",
            tint = Color.Gray
        )
        Spacer(Modifier.width(4.dp))
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.weight(1f))
        Text(
            ">",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Divider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = Color.Gray.copy(alpha = 0.5f),
        thickness = 1.dp
    )
}

@Composable
fun SellerDescription() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Seller Description",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(R.drawable.con),
                contentDescription = "Menu",
                tint = Color.Black
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Condition: Gently Used",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Spacer(Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(R.drawable.col),
                contentDescription = "Menu",
                tint = Color.Black
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Color: Khaki",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Четкие джинсты атвечаююю, сам за 1000 брал просто по размеру не подошли",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Tags",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            listOf(
                "#SUPREME", "#STREETWEAR", "#ACNESTUDIOS", "#GUCCI",
                "#VIVIENNEWESTWOOD", "#PRADA", "#MONCLER", "#CARHARTT"
            ).forEach { tag ->
                OutlinedButton(
                    onClick = { /* Navigate to tag */ },
                    modifier = Modifier.height(32.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black,
                        containerColor = Color.Transparent
                    ),
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(3.dp)
                ) {
                    Text(tag, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}



