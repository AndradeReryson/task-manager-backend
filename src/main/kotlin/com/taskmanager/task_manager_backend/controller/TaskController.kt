package com.taskmanager.task_manager_backend.controller

import com.taskmanager.task_manager_backend.models.Task
import com.taskmanager.task_manager_backend.service.TaskService
import com.taskmanager.task_manager_backend.service.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks") 
class TaskController(
    private val taskService: TaskService
) {

    // POST /api/tasks
    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        return try {
            val newTask = taskService.saveTask(task)
            // Retorna 201 Created se for bem-sucedido
            ResponseEntity(newTask, HttpStatus.CREATED)
        } catch (e: ResourceNotFoundException) {
            // Retorna 404 Not Found se o Projeto ou o Colaborador não for encontrado
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getTaskById(id)
        return if (task != null) {
            ResponseEntity(task, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    
    // GET /api/tasks/project/{projectId}
    @GetMapping("/project/{projectId}")
    fun getTasksByProjectId(@PathVariable projectId: Long): ResponseEntity<List<Task>> {
        val tasks = taskService.getTasksByProjectId(projectId)
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody taskDetails: Task): ResponseEntity<Task> {
        val existingTask = taskService.getTaskById(id)
        
        return if (existingTask != null) {
            // Lógica de atualização
            existingTask.title = taskDetails.title
            existingTask.description = taskDetails.description
            existingTask.dueDate = taskDetails.dueDate
            existingTask.priority = taskDetails.priority
            existingTask.status = taskDetails.status
            existingTask.responsibleEmployeeId = taskDetails.responsibleEmployeeId
            
            val updatedTask = taskService.saveTask(existingTask)
            ResponseEntity(updatedTask, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}