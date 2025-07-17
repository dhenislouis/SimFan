package com.example.simfan.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simfan.R
import com.google.android.material.progressindicator.CircularProgressIndicator

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val profile by viewModel.profileState.collectAsState()

    LaunchedEffect(Unit) { viewModel.fetchProfile() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        profile?.let {
            ProfileHeaderSection(name = it.name, email = it.email)
            ProfileStatusSection(points = it.points, expiry = it.expiry)
        } ?: run {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        ProfileFeatureSection()
        ProfileHelpSection()
    }
}

@Composable
fun ProfileHeaderSection(name: String, email: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_avatar_placeholder),
            contentDescription = "Avatar",
            modifier = Modifier.size(72.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = email, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Kode Referal: RV07162",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ProfileStatusSection(points: Int, expiry: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Gold Member", fontWeight = FontWeight.Bold)
            Text(text = "$points Points", fontSize = 14.sp)
            Text(text = "Berlaku hingga $expiry", fontSize = 12.sp)
        }
    }
}

@Composable
fun ProfileFeatureSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Fitur", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        ProfileMenuItem(title = "Edit Profil")
        ProfileMenuItem(title = "Pengaturan")
        ProfileMenuItem(title = "Keluar")
    }
}

@Composable
fun ProfileHelpSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Informasi & Bantuan", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        ProfileMenuItem(title = "FAQ")
        ProfileMenuItem(title = "Hubungi Kami")
    }
}

@Composable
fun ProfileMenuItem(title: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
