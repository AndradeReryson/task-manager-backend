// ProjectController.kt (Refatorado)

package com.taskmanager.task_manager_backend.controller

import com.taskmanager.task_manager_backend.dto.* 
import com.taskmanager.task_manager_backend.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(
    private val projectService: ProjectService
) {
    
    // POST /api/projects
    @PostMapping
    fun createProject(@RequestBody projectDto: ProjectRequestDTO): ResponseEntity<ProjectResponseDTO> {
        val projectEntity = projectDto.toEntity()
        val newProject = projectService.saveProject(projectEntity)

        return ResponseEntity(newProject.toResponseDTO(), HttpStatus.CREATED)
    }

    // GET /api/projects 
    @GetMapping
    fun getAllProjects(): ResponseEntity<List<ProjectResponseDTO>> {
        val projects = projectService.getAllProjects()

        val responseDtos = projects.map { it.toResponseDTO() }
        return ResponseEntity(responseDtos, HttpStatus.OK)
    }

    // GET /api/projects/{id} 
    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Long): ResponseEntity<ProjectResponseDTO> {
        val project = projectService.getProjectById(id)
        
        return if (project != null) {
            ResponseEntity(project.toResponseDTO(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    
    // PUT /api/projects/{id} 
    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody projectDto: ProjectRequestDTO): ResponseEntity<ProjectResponseDTO> {
        val existingProject = projectService.getProjectById(id)
        
        return if (existingProject != null) {
            existingProject.name = projectDto.name
            existingProject.description = projectDto.description
            existingProject.startDate = projectDto.startDate
            existingProject.endDate = projectDto.endDate
            existingProject.status = projectDto.status ?: existingProject.status // Mantém o status atual se o DTO for nulo
            existingProject.budget = projectDto.budget

            val updatedProject = projectService.saveProject(existingProject)
            ResponseEntity(updatedProject.toResponseDTO(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}