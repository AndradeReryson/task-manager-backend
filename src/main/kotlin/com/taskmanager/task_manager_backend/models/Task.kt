package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Column(nullable = false)
    var dueDate: LocalDate,

    @Enumerated(EnumType.STRING)
    var priority: TaskPriority = TaskPriority.MEDIA,

    @Enumerated(EnumType.STRING)
    var status: TaskStatus = TaskStatus.PENDENTE,
    
    // Relacionamento com Project (Chave Estrangeira)
    @Column(nullable = false)
    var projectId: Long, 
    
    // Relacionamento com Employee (Responsável)
    var responsibleEmployeeId: Long? = null 
)

enum class TaskPriority {
    BAIXA,
    MEDIA,
    ALTA,
    CRITICA
}

enum class TaskStatus {
    PENDENTE,
    EM_ANDAMENTO,
    REVISAO,
    CONCLUIDA,
    CANCELADA
}