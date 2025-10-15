package com.taskmanager.task_manager_backend.service

import com.taskmanager.task_manager_backend.models.Project
import com.taskmanager.task_manager_backend.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository 
) {

    fun saveProject(project: Project): Project {
        return projectRepository.save(project) 
    }

    fun getAllProjects(): List<Project> {
        return projectRepository.findAll()
    }

    fun getProjectById(id: Long): Project? {
        return projectRepository.findById(id).orElse(null) 
    }
}