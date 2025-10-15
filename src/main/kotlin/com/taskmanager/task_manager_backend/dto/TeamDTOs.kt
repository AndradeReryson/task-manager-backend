package com.taskmanager.task_manager_backend.dto

import com.taskmanager.task_manager_backend.models.Team

// DTO de Entrada: Recebe dados para POST/PUT
data class TeamRequestDTO(
    val name: String,
    val colorBadge: String // Ex: "#FF5733"
)

// DTO de Saída: Envia dados para o front-end
data class TeamResponseDTO(
    val id: Long,
    val name: String,
    val colorBadge: String
)

// Mapeador: Converte DTO ↔ Entidade
fun TeamRequestDTO.toEntity(): Team {
    return Team(
        name = this.name,
        colorBadge = this.colorBadge
    )
}

fun Team.toResponseDTO(): TeamResponseDTO {
    return TeamResponseDTO(
        id = this.id ?: throw IllegalStateException("Team ID must not be null for response"),
        name = this.name,
        colorBadge = this.colorBadge
    )
}