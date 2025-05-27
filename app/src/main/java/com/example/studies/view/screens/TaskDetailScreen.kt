//package com.example.studies.view.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
////import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.studies.ui.theme.StudiesTheme // Importe o seu tema correto
//import com.example.studies.viewmodel.TaskViewModel
//import java.time.format.DateTimeFormatter
//import java.time.format.FormatStyle
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TaskDetailScreen(
//    navController: NavController,
//    viewModel: TaskViewModel = viewModel() // Injeta o ViewModel
//) {
//    // Observa a tarefa selecionada do ViewModel
////    val task = viewModel.selectedTask.observeAsState().value
//
//    // Se não houver tarefa selecionada, volte para a lista ou mostre um erro
//    if (task == null) {
//        // Você pode navegar de volta ou exibir uma mensagem
//        navController.popBackStack() // Volta para a tela anterior
//        return
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Detalhes da Tarefa") },
//                // Adicione um botão de voltar se quiser
//                // navigationIcon = {
//                //     IconButton(onClick = { navController.popBackStack() }) {
//                //         Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
//                //     }
//                // }
//            )
//        },
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues) // Aplica o padding do Scaffold
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = task.name,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Divider(modifier = Modifier.fillMaxWidth(0.8f))
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = task.discipline,
//                    fontSize = 18.sp,
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//
//                Text(
//                    text = "Descrição:",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    modifier = Modifier.align(Alignment.Start)
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = task.description,
//                    fontSize = 16.sp,
//                    modifier = Modifier.align(Alignment.Start)
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                task.dueDate?.let {
////                    Text(
////                        text = "Prazo de entrega: ${format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))}",
////                        fontSize = 14.sp,
////                        modifier = Modifier.align(Alignment.Start)
////                    )
//                }
//                task.dueTime?.let {
////                    Text(
////                        text = "Horário: ${it.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))}",
////                        fontSize = 14.sp,
////                        modifier = Modifier.align(Alignment.Start)
////                    )
//                }
//
//                Spacer(modifier = Modifier.weight(1f)) // Empurra os botões para baixo
//
//                Button(
//                    onClick = {
//                        viewModel.completeTask(task.id)
//                        navController.popBackStack() // Volta para a tela anterior
//                    },
//                    enabled = !task.isCompleted, // Desabilita se já estiver concluída
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                ) {
//                    Text(text = if (task.isCompleted) "Concluída" else "Concluir")
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                Button(
//                    onClick = {
//                        viewModel.deleteTask(task.id)
//                        navController.popBackStack() // Volta para a tela anterior após deletar
//                    },
//                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                ) {
//                    Text(text = "Deletar", color = MaterialTheme.colorScheme.onError)
//                }
//            }
//        }
//    )
//}