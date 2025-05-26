package com.example.studies.view.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studies.ui.theme.StudiesTheme
import com.example.studies.view.components.Footer

@Composable
fun DisciplinesScreen(navController: NavController) {
    // Simulação de dados para as disciplinas
    val disciplines = listOf(
        "Disciplina 1",
        "Disciplina 2",
        "Disciplina 3",
        "Disciplina 4"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(30.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Suas Disciplinas",
                        fontSize = 30.sp,
                        color = Color(0xFF0E0E0E),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    FloatingActionButton(
                        onClick = { /*  Navegar para a tela de adicionar nova tarefa */ },
                        containerColor = Color(0xFF6B6969), // Cor do FAB
                        contentColor = Color.White,
                        modifier = Modifier.size(48.dp) // Ajustar o tamanho do FAB
                    ) {
                        Icon(Icons.Filled.Add, "Adicionar nova disciplina")
                    }
                }


                LazyColumn {
                    items(disciplines) { discipline ->
                        DisciplineCard(discipline = discipline)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            Footer(navController = navController, currentRoute = "disciplines")
        }
    }
}

@Composable
fun DisciplineCard(discipline: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF6B6969), RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Transparente para o gradiente de fundo aparecer
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone da disciplina (pode ser substituído por uma imagem real)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(1.dp, Color(0xFF6B6969), RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                // Aqui você pode colocar um Image composable para a imagem da disciplina
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = discipline, fontSize = 20.sp, color = Color(0xFF424242))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisciplinesScreen() {
    StudiesTheme {
        val navController = rememberNavController()
        DisciplinesScreen(navController = navController)
    }
}