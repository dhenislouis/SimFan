package com.example.simfan.ui.beranda

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simfan.R

@Composable
fun BerandaScreen(viewModel: BerandaViewModel = hiltViewModel()) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { BerandaHeaderSection() }
        item { BerandaPromoSection() }
        item { BerandaProductSection() }
        item { BerandaArticleSection() }
    }
}

@Composable
fun BerandaHeaderSection() {
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
fun BerandaPromoSection() {
    Column {
        Text("Promo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        PromoCardItem(title = "Promo Akhir Tahun", description = "Cashback hingga 50% untuk transaksi tertentu.")
    }
}

@Composable
fun BerandaProductSection() {
    Column {
        Text("Produk Unggulan", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        PromoCardItem(title = "Deposito Berjangka", description = "Bunga hingga 6% per tahun.")
    }
}

@Composable
fun BerandaArticleSection() {
    Column {
        Text("Artikel Edukasi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        PromoCardItem(title = "Tips Mengatur Keuangan", description = "Pelajari cara mengatur keuangan agar lebih hemat.")
    }
}

@Composable
fun PromoCardItem(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
