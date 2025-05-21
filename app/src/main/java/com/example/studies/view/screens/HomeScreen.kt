package com.example.studies.view.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studies.view.components.Footer
import com.example.studies.ui.theme.StudiesTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class Subject(val name: String, val time: String, val location: String)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val userName = remember { mutableStateOf(loadUserName(context)) }

    // Simulação de dados para as aulas do dia
    val todaySubjects = listOf(
        Subject("Disciplina 1", "08:00 - 10:00", "Local"),
        Subject("Disciplina 2", "14:00 - 16:00", "Local"),
        Subject("Disciplina 3", "16:00 - 18:00", "Local")
    )

    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM", Locale("pt", "BR")))

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = "Olá, ${userName.value}!",
                fontSize = 24.sp,
                color = Color(0xFF424242),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Aulas do dia",
                    fontSize = 20.sp,
                    color = Color(0xFF424242)
                )
                Text(
                    text = currentDate,
                    fontSize = 18.sp,
                    color = Color(0xFF424242)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(todaySubjects) { subject ->
                    SubjectCard(subject = subject)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        Footer(navController = navController, currentRoute = "home")
    }
}

@Composable
fun SubjectCard(subject: Subject) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF6B6969), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text = subject.time, fontSize = 16.sp, color = Color(0xFF424242))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = subject.name, fontSize = 18.sp, color = Color(0xFF424242))
        Text(text = subject.location, fontSize = 14.sp, color = Color(0xFF6B6969))
    }
}

fun loadUserName(context: Context): String {
    val sharedPref = context.getSharedPreferences("studies_prefs", Context.MODE_PRIVATE)
    return sharedPref.getString("user_name", "User") ?: "User"
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    StudiesTheme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}