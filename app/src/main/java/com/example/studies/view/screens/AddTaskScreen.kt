package com.example.studies.view.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
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

    // Date Picker Dialog
    val year by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    val month by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    val day by remember { mutableStateOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
        }, year, month, day
    )

    // Time Picker Dialog
    val hour by remember { mutableStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    val minute by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
            selectedTime = LocalTime.of(selectedHour, selectedMinute)
        }, hour, minute, true // is24HourView
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nova Tarefa") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = discipline,
                    onValueChange = { discipline = it },
                    label = { Text("Disciplina") },
                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Futuramente, abrir um DropdownMenu para seleção */ }
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4 // Para permitir mais texto
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
                    OutlinedTextField(
                        value = selectedDate?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) ?: "DD/MM/AAAA",
                        onValueChange = { /* Não é editável diretamente */ },
                        label = { Text("Data") },
                        readOnly = true,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { datePickerDialog.show() }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = selectedTime?.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) ?: "HH:MM",
                        onValueChange = { /* Não é editável diretamente */ },
                        label = { Text("Horário") },
                        readOnly = true,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { timePickerDialog.show() }
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Empurra o botão para baixo

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
                        navController.popBackStack() // Volta para a tela anterior
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