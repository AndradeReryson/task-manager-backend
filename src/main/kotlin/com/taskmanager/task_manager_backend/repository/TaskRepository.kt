package com.taskmanager.task_manager_backend.repository

import com.taskmanager.task_manager_backend.models.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.taskmanager.task_manager_backend.models.TaskStatus

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    
    fun findByProjectId(projectId: Long): List<Task>
    
    fun findByStatus(status: TaskStatus): List<Task>
}