package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*

@Entity
@Table(name = "teams")
data class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String, // Nome do departamento (Ex: "Estruturas", "Hidráulica", "Gerenciamento")

    @Column(nullable = false)
    var colorBadge: String // Para as badges coloridas (Ex: "#FF5733" ou "RED")
)