package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity 
@Table(name = "projects") 
data class Project(

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    val id: Long? = null, 

    @Column(nullable = false, unique = true)
    var name: String, 

    @Column(columnDefinition = "TEXT") 
    var description: String? = null,

    @Column(nullable = false)
    var startDate: LocalDate,

    var endDate: LocalDate? = null,

    @Enumerated(EnumType.STRING) 
    var status: ProjectStatus = ProjectStatus.PLANEJAMENTO,

    // Representa o orçamento total (ideal para valores monetários)
    var budget: BigDecimal? = null,
    
    // Futuro relacionamento com Team líder, por enquanto deixamos como um campo simples
    var responsibleTeamId: Long? = null 

    // Mais campos e relacionamentos serão adicionados conforme avançamos
)

// Enum para o status do projeto (Melhor que usar String solta)
enum class ProjectStatus {
    PLANEJAMENTO,
    EM_EXECUCAO,
    PAUSADO,
    CONCLUIDO,
    CANCELADO
}