package com.taskmanager.task_manager_backend.service

import com.taskmanager.task_manager_backend.models.Team
import com.taskmanager.task_manager_backend.repository.TeamRepository
import org.springframework.stereotype.Service

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {
    fun saveTeam(team: Team): Team {
        if (team.name.isBlank()) {
            throw IllegalArgumentException("O nome do time não pode ser vazio.")
        }
        
        return teamRepository.save(team)
    }

    fun getAllTeams(): List<Team> {
        return teamRepository.findAll()
    }

    fun getTeamById(id: Long): Team? {
        return teamRepository.findById(id).orElse(null)
    }
}