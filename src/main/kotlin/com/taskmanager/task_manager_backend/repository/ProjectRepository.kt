package com.taskmanager.task_manager_backend.repository

import com.taskmanager.task_manager_backend.models.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {
  
}