package com.example.studies.view.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker // Mantenha este import
import android.widget.TimePicker // Mantenha este import
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.studies.data.Task
import com.example.studies.view.components.Footer
import com.example.studies.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    var taskName by remember { mutableStateOf("") }
    var discipline by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }

    val context = LocalContext.current
    // Não precisamos mais dos states year, month, day, hour, minute separados aqui

    // Date Picker Dialog
    val datePickerDialog = remember {
        val calendar = Calendar.getInstance() // Pegar a instância atual para valores iniciais
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int -> // month é 0-indexed aqui
                selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            },
            // Valores iniciais padrão (serão atualizados antes de mostrar de qualquer forma)
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    // Time Picker Dialog
    val timePickerDialog = remember {
        val calendar = Calendar.getInstance() // Pegar a instância atual para valores iniciais
        TimePickerDialog(
            context,
            { _: TimePicker, hourOfDay: Int, minute: Int ->
                selectedTime = LocalTime.of(hourOfDay, minute)
            },
            // Valores iniciais padrão (serão atualizados antes de mostrar de qualquer forma)
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // is24HourView
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nova Tarefa") }
            )
        },
        bottomBar = {
            Footer(navController = navController, currentRoute = "calendar")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = discipline,
                    onValueChange = { discipline = it },
                    label = { Text("Disciplina") },
                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Futuramente, abrir um DropdownMenu para seleção */ }
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Prazo de entrega", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Campo de Data com Box clicável
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { // A lógica do clique para abrir o seletor permanece aqui
                                val calendar = Calendar.getInstance()
                                val yearToShow = selectedDate?.year ?: calendar.get(Calendar.YEAR)
                                val monthToShow = selectedDate?.monthValue?.minus(1) ?: calendar.get(Calendar.MONTH)
                                val dayToShow = selectedDate?.dayOfMonth ?: calendar.get(Calendar.DAY_OF_MONTH)
                                datePickerDialog.updateDate(yearToShow, monthToShow, dayToShow)
                                datePickerDialog.show()
                            }
                            .border( // Adiciona uma borda para simular o OutlinedTextField
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline, // Cor da borda padrão
                                shape = MaterialTheme.shapes.extraSmall // Forma padrão (geralmente cantos arredondados)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp) // Padding interno similar ao de um TextField
                    ) {
                        Column { // Para organizar o rótulo e o valor
                            Text(
                                text = "Data", // Seu texto de rótulo
                                color = MaterialTheme.colorScheme.primary, // Cor para o rótulo (pode ajustar)
                                style = MaterialTheme.typography.bodySmall // Estilo para o rótulo (pode ajustar)
                            )
                            Spacer(modifier = Modifier.height(2.dp)) // Pequeno espaço entre rótulo e valor
                            Text(
                                text = selectedDate?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) ?: "DD/MM/AAAA",
                                color = MaterialTheme.colorScheme.onSurface, // Cor do texto principal (não "apagado")
                                style = MaterialTheme.typography.bodyLarge // Estilo do texto principal (pode ajustar)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Campo de Horário com Box clicável
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                val calendar = Calendar.getInstance()
                                val hourToShow = selectedTime?.hour ?: calendar.get(Calendar.HOUR_OF_DAY)
                                val minuteToShow = selectedTime?.minute ?: calendar.get(Calendar.MINUTE)
                                timePickerDialog.updateTime(hourToShow, minuteToShow)
                                timePickerDialog.show()
                            }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.extraSmall
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Column {
                            Text(
                                text = "Horário",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = selectedTime?.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) ?: "HH:MM",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        val newTask = Task(
                            name = taskName,
                            discipline = discipline,
                            description = description,
                            dueDate = selectedDate,
                            dueTime = selectedTime
                        )
                        viewModel.addTask(newTask)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = taskName.isNotBlank() && discipline.isNotBlank() && description.isNotBlank()
                ) {
                    Text(text = "Adicionar")
                }
            }
        }
    )
}