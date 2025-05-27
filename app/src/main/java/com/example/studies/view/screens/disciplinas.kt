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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studies.ui.theme.StudiesTheme
import com.example.studies.view.components.Footer
import com.example.studies.R

@Composable

fun DisciplinesScreen(navController: NavController) {

    val disciplines = listOf(
        stringResource(id = R.string.D1),
        stringResource(id = R.string.D2),
        stringResource(id = R.string.D3),
        stringResource(id = R.string.D4)
    )


    Column(modifier = Modifier.fillMaxSize()) {

        Column(

            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(45.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.DisciplinasTitulo),
                    fontSize = 30.sp,
                    color = Color(0xFF0E0E0E)
                )

                FloatingActionButton(
                    onClick = { /* Navegar para a tela de adicionar nova tarefa */ },
                    containerColor = Color(0xFF6B6969),
                    contentColor = Color.White,
                    modifier = Modifier.size(48.dp)

                ) {

                    Icon(Icons.Filled.Add, stringResource(id = R.string.AddDisciplina))

                }

            }



            Spacer(modifier = Modifier.height(7.dp))



            HorizontalDivider(
                color = Color(0xFF0E0E0E),
                thickness = 2.dp,
                modifier = Modifier.padding(bottom = 45.dp)
            )



            LazyColumn {
                items(disciplines) { discipline ->
                    DisciplineCard(discipline = discipline)
                    Spacer(modifier = Modifier.height(35.dp))

                }

            }

        }

        Footer(navController = navController, currentRoute = "disciplines")

    }

}



@Composable
fun DisciplineCard(discipline: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5.dp, Color(0xFF0E0E0E), RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(15.dp)

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(

                modifier = Modifier
                    .size(48.dp)
                    .border(1.dp, Color(0xFF0E0E0E), RoundedCornerShape(7.dp))
                    .padding(4.dp)

            ) {

                    // Imagem da disciplina

            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(text = discipline, fontSize = 27.sp, color = Color(0xFF0E0E0E))

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