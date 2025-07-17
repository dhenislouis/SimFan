package com.example.simfan.ui.dashboard

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simfan.R
import com.example.simfan.ui.beranda.BerandaViewModel
import com.example.simfan.ui.promo.PromoViewModel
import com.example.simfan.ui.produk.ProdukViewModel

@Composable
fun HomeDashboardScreen(
    berandaViewModel: BerandaViewModel = viewModel(),
    promoViewModel: PromoViewModel = viewModel(),
    productViewModel: ProdukViewModel = viewModel()
) {
    val articles by berandaViewModel.articlesState.collectAsState()
    val promos by promoViewModel.promoListState.collectAsState()
    val products by productViewModel.productsState.collectAsState()

    LaunchedEffect(Unit) {
        berandaViewModel.fetchArticles()
        promoViewModel.fetchPromoList()
        productViewModel.fetchProducts()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { DashboardHeaderSection() }
        item { QuickActionSection() }
        item { PromoSection(promos) }
        item { ProductSection(products) }
        item { ArticleSection(articles) }
    }
}

@Composable
fun DashboardHeaderSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_avatar_placeholder),
            contentDescription = "Avatar",
            modifier = Modifier.size(72.dp).clip(MaterialTheme.shapes.medium)
        )
        Text("Selamat Pagi, Ayu", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("Saldo: Rp 12.500.000", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun QuickActionSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton("Top Up") {}
        ActionButton("Bayar") {}
        ActionButton("Scan QR") {}
    }
}

@Composable
fun ActionButton(title: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
        Text(title)
    }
}

@Composable
fun PromoSection(promos: List<com.example.simfan.ui.promo.Promo>) {
    Column {
        Text("Promo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        promos.forEach { promo ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(promo.title, fontWeight = FontWeight.Bold)
                    Text(promo.description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun ProductSection(products: List<com.example.simfan.ui.produk.Product>) {
    Column {
        Text("Produk Unggulan", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        products.forEach { product ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(product.title, fontWeight = FontWeight.Bold)
                    Text(product.description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Bunga: ${product.interestRate}%", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun ArticleSection(articles: List<com.example.simfan.ui.beranda.Article>) {
    Column {
        Text("Artikel Edukasi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        articles.forEach { article ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(article.title, fontWeight = FontWeight.Bold)
                    Text(article.description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}
