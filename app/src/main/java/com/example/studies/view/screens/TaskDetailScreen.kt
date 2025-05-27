package com.example.studies.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign // Importar TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studies.data.Task
import com.example.studies.ui.theme.StudiesTheme
import com.example.studies.view.components.Footer
import com.example.studies.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    val task = viewModel.selectedTask.observeAsState().value

    if (task == null) {
        if (!LocalInspectionMode.current) {
            navController.popBackStack()
        }
        return
    }

    Scaffold(
        // topBar = null, // Mantendo sem TopAppBar conforme sua preferência anterior
        bottomBar = {
            Footer(navController = navController, currentRoute = "taskDetail")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(start = 16.dp, end = 16.dp, top = 100.dp),
                horizontalAlignment = Alignment.Start // Nome e Disciplina ficam à esquerda
            ) {
                Text(
                    text = task.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 27.dp, bottom = 5.dp)
                )
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp)
                )
                Text(
                    text = task.discipline,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 27.dp, bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(32.dp)) // Removido para juntar com a primeira seção centralizada

                // Seção Descrição - Centralizada
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally // Centraliza os Textos filhos
                ) {
                    Text(
                        text = "Descrição:",
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.description,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left // Centraliza o texto da descrição se for multilinhas
                        // Ou TextAlign.Start para alinhar à esquerda dentro do bloco centralizado
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Seção Prazo de entrega - Centralizada
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Espaçamento da seção
                    horizontalAlignment = Alignment.CenterHorizontally // Centraliza os Textos filhos
                ) {
                    Text(
                        text = "Prazo de entrega:",
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val dateTimeString = remember(task.dueDate, task.dueTime) {
                        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        val timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                        val dateStr = task.dueDate?.format(dateFormatter)
                        val timeStr = task.dueTime?.format(timeFormatter)
                        when {
                            dateStr != null && timeStr != null -> "$dateStr - $timeStr"
                            dateStr != null -> dateStr
                            timeStr != null -> "Hora: $timeStr"
                            else -> null
                        }
                    }
                    dateTimeString?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center // Centraliza o texto
                        )
                    } ?: Text(
                        text = "Não definido",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center // Centraliza o texto
                    )
                }
                Spacer(modifier = Modifier.height(16.dp)) // Removido

                // Seção Status - Centralizada
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally // Centraliza os Textos filhos
                ) {
                    Text(
                        text = "Status:",
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (task.isCompleted) "Concluída" else "Pendente",
                        fontSize = 20.sp,
                        color = if (task.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center // Centraliza o texto
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Empurra os botões para baixo

                Button(
                    onClick = {
                        viewModel.completeTask(task.id)
                        navController.popBackStack()
                    },
                    enabled = !task.isCompleted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp, start = 100.dp, end = 100.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if (task.isCompleted) "Concluída" else "Concluir",
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.deleteTask(task.id)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, start = 100.dp, end = 100.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Deletar", color = MaterialTheme.colorScheme.onError,
                        fontSize = 18.sp
                    )
                }
            }
        }
    )
}

// Previews (mantenha ou ajuste seus previews para refletir as mudanças)
@Preview(showBackground = true, name = "TaskDetailScreen Centered Sections")
@Composable
fun TaskDetailScreenCenteredPreview() {
    StudiesTheme {
        val navController = rememberNavController()
        val viewModel = viewModel<TaskViewModel>()
        val mockTaskForPreview = Task(
            id = "previewTask1",
            name = "Título da Tarefa (Esquerda)",
            discipline = "Disciplina (Esquerda)",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            dueDate = LocalDate.now().plusDays(1),
            dueTime = LocalTime.of(18, 45),
            isCompleted = false
        )
        LaunchedEffect(key1 = Unit) { viewModel.selectTask(mockTaskForPreview) }
        TaskDetailScreen(navController = navController, viewModel = viewModel)
    }
}