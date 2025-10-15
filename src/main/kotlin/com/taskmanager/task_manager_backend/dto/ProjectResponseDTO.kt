package com.taskmanager.task_manager_backend.dto

import com.taskmanager.task_manager_backend.models.ProjectStatus
import java.math.BigDecimal
import java.time.LocalDate

data class ProjectResponseDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val status: ProjectStatus,
    val budget: BigDecimal?,
    val responsibleTeamId: Long?
)