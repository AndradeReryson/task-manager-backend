package com.taskmanager.task_manager_backend.dto

import com.taskmanager.task_manager_backend.models.ProjectStatus
import com.taskmanager.task_manager_backend.models.Project

// Função de Extensão: Converte DTO de entrada para a Entidade JPA
fun ProjectRequestDTO.toEntity(): Project {
    return Project(
        name = this.name,
        description = this.description,
        startDate = this.startDate,
        endDate = this.endDate,
        status = this.status ?: ProjectStatus.PLANEJAMENTO,
        budget = this.budget,
        responsibleTeamId = this.responsibleTeamId
    )
}

// Função de Extensão: Converte a Entidade JPA para o DTO de saída
fun Project.toResponseDTO(): ProjectResponseDTO {
    return ProjectResponseDTO(
        id = this.id ?: throw IllegalStateException("Project ID must not be null"),
        name = this.name,
        description = this.description,
        startDate = this.startDate,
        endDate = this.endDate,
        status = this.status,
        budget = this.budget,
        responsibleTeamId = this.responsibleTeamId
    )
}