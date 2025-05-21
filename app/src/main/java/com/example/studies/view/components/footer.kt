package com.example.studies.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studies.ui.theme.StudiesTheme

@Composable
fun Footer(navController: NavController, currentRoute: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF424242)), // Cor do footer
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FooterItem(
            icon = Icons.Default.Home,
            label = "Home",
            isSelected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        FooterItem(
            icon = Icons.AutoMirrored.Filled.List,
            label = "Disciplinas",
            isSelected = currentRoute == "disciplines",
            onClick = { navController.navigate("disciplines") }
        )
        FooterItem(
            icon = Icons.Default.DateRange,
            label = "Calendário",
            isSelected = currentRoute == "calendar", // Rota fictícia para a tela de calendário
            onClick = { /* Implementar navegação para calendário */ }
        )
        FooterItem(
            icon = Icons.Default.Settings,
            label = "Configurações",
            isSelected = currentRoute == "settings", // Rota fictícia para a tela de configurações
            onClick = { /* Implementar navegação para configurações */ }
        )
    }
}

@Composable
fun FooterItem(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color.White else Color(0xFFCDCDCD), // Destaque para o selecionado
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            color = if (isSelected) Color.White else Color(0xFFCDCDCD),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStudiesFooter() {
    StudiesTheme {
        val navController = rememberNavController()
        Footer(navController = navController, currentRoute = "home")
    }
}