package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String, // Usado para login e deve ser único

    var role: String, // Cargo (Ex: Engenheiro Civil, Mestre de Obras)

    @Column(nullable = false)
    var admissionDate: LocalDate,

    var dismissalDate: LocalDate? = null, // Para controle de desligamento

    @Column(nullable = false)
    var isActive: Boolean = true, // Status ativo/inativo

    // Relacionamento com Team (ainda não vamos criar o objeto Team, usamos o ID por enquanto)
    var teamId: Long? = null 
)