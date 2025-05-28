package com.example.studies.data

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(), // ID único para cada tarefa
    var name: String,
    var discipline: String,
    var description: String,
    var dueDate: LocalDate?, // Data de entrega (pode ser nula)
    var dueTime: LocalTime?, // Hora de entrega (pode ser nula)
    var isCompleted: Boolean = false // Para o botão "Concluir"
)