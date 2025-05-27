package com.example.studies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studies.data.Task
import java.time.LocalDate
import java.time.LocalTime

class TaskViewModel : ViewModel() {

    // Lista de tarefas observável
    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> = _tasks

    // Tarefa atualmente selecionada para a tela de detalhes
    private val _selectedTask = MutableLiveData<Task?>(null)
    val selectedTask: LiveData<Task?> = _selectedTask

    init {
        // Dados mockados iniciais
        addMockTasks()
    }

    private fun addMockTasks() {
        _tasks.value?.add(
            Task(
                name = "Tarefa 1",
                discipline = "Matemática",
                description = "Revisar capítulos 1 a 3 do livro didático para a prova final.",
                dueDate = LocalDate.now().plusDays(2),
                dueTime = LocalTime.of(18, 0)
            )
        )
        _tasks.value?.add(
            Task(
                name = "Tarefa 2",
                discipline = "Disciplina 1",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                dueDate = LocalDate.now().plusDays(5),
                dueTime = LocalTime.of(23, 59)
            )
        )
        _tasks.value?.add(
            Task(
                name = "Estudar Jetpack Compose",
                discipline = "Desenvolvimento Mobile",
                description = "Fazer os tutoriais da documentação oficial do Google sobre Jetpack Compose e ViewModels. Praticar a criação de interfaces responsivas.",
                dueDate = LocalDate.now().plusWeeks(1),
                dueTime = null // Sem hora específica
            )
        )
        _tasks.value = _tasks.value // Garante que o LiveData seja atualizado
    }

    fun addTask(task: Task) {
        val currentTasks = _tasks.value ?: mutableListOf()
        currentTasks.add(task)
        _tasks.value = currentTasks // Notifica observadores
    }

    fun updateTask(updatedTask: Task) {
        val currentTasks = _tasks.value ?: mutableListOf()
        val index = currentTasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            currentTasks[index] = updatedTask
            _tasks.value = currentTasks
        }
    }

    fun deleteTask(taskId: String) {
        val currentTasks = _tasks.value ?: mutableListOf()
        _tasks.value = currentTasks.filter { it.id != taskId }.toMutableList()
        if (_selectedTask.value?.id == taskId) {
            _selectedTask.value = null // Limpa a tarefa selecionada se ela for deletada
        }
    }

    fun completeTask(taskId: String) {
        val currentTasks = _tasks.value ?: mutableListOf()
        val taskToComplete = currentTasks.find { it.id == taskId }
        taskToComplete?.let {
            it.isCompleted = true
            updateTask(it) // Atualiza a tarefa na lista
        }
    }

    fun selectTask(task: Task?) {
        _selectedTask.value = task
    }
}