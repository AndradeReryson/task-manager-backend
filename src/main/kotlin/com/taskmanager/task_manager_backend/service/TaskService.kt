package com.taskmanager.task_manager_backend.service

import com.taskmanager.task_manager_backend.models.Task
import com.taskmanager.task_manager_backend.repository.TaskRepository
import com.taskmanager.task_manager_backend.repository.ProjectRepository 
import com.taskmanager.task_manager_backend.repository.EmployeeRepository 
import org.springframework.stereotype.Service

class ResourceNotFoundException(message: String) : RuntimeException(message)

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val projectRepository: ProjectRepository, 
    private val employeeRepository: EmployeeRepository
) {
    fun saveTask(task: Task): Task {
        // Futura lógica de validação:
        if (!projectRepository.existsById(task.projectId)) {
            throw ResourceNotFoundException("Projeto com ID ${task.projectId} não encontrado. A tarefa não pode ser salva.")
        }

        // VERIFICAÇÃO 2: Se um responsável for atribuído, o employeeId deve existir.
        task.responsibleEmployeeId?.let { employeeId ->
            if (!employeeRepository.existsById(employeeId)) {
                throw ResourceNotFoundException("Colaborador com ID $employeeId não encontrado. A tarefa não pode ser salva.")
            }
        }

        return taskRepository.save(task) 
    }

    fun getAllTasks(): List<Task> {
        return taskRepository.findAll()
    }

    fun getTasksByProjectId(projectId: Long): List<Task> {
        return taskRepository.findByProjectId(projectId)
    }

    fun getTaskById(id: Long): Task? {
        return taskRepository.findById(id).orElse(null)
    }
}