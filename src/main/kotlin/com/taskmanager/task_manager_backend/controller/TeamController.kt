package com.taskmanager.task_manager_backend.controller

import com.taskmanager.task_manager_backend.dto.*
import com.taskmanager.task_manager_backend.service.TeamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/teams")
class TeamController(
    private val teamService: TeamService
) {
    
    // POST /api/teams
    @PostMapping
    fun createTeam(@RequestBody teamDto: TeamRequestDTO): ResponseEntity<TeamResponseDTO> {
        return try {
            val teamEntity = teamDto.toEntity()
            val newTeam = teamService.saveTeam(teamEntity)
            ResponseEntity(newTeam.toResponseDTO(), HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST) 
        }
    }

    // GET /api/teams
    @GetMapping
    fun getAllTeams(): ResponseEntity<List<TeamResponseDTO>> {
        val teams = teamService.getAllTeams()
        val responseDtos = teams.map { it.toResponseDTO() }
        return ResponseEntity(responseDtos, HttpStatus.OK)
    }

    // GET /api/teams/{id}
    @GetMapping("/{id}")
    fun getTeamById(@PathVariable id: Long): ResponseEntity<TeamResponseDTO> {
        val team = teamService.getTeamById(id)
        
        return if (team != null) {
            ResponseEntity(team.toResponseDTO(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    // PUT /api/teams/{id}
    @PutMapping("/{id}")
    fun updateTeam(@PathVariable id: Long, @RequestBody teamDto: TeamRequestDTO): ResponseEntity<TeamResponseDTO> {
        val existingTeam = teamService.getTeamById(id)
        
        return if (existingTeam != null) {
            return try {
                existingTeam.name = teamDto.name
                existingTeam.colorBadge = teamDto.colorBadge

                val updatedTeam = teamService.saveTeam(existingTeam)
                ResponseEntity(updatedTeam.toResponseDTO(), HttpStatus.OK)
            } catch (e: IllegalArgumentException) {
                ResponseEntity(HttpStatus.BAD_REQUEST) 
            }
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}