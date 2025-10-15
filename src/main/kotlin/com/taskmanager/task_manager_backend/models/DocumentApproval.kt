package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "document_approvals")
data class DocumentApproval(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    // Chave Estrangeira para a Versão específica que está sendo aprovada
    @Column(nullable = false)
    var documentVersionId: Long,
    
    @Column(nullable = false)
    var stage: Int, // Estágio do fluxo (1, 2, ou 3)
    
    @Column(nullable = false)
    var approverEmployeeId: Long, // ID do colaborador responsável por aprovar
    
    @Enumerated(EnumType.STRING)
    var status: ApprovalStatusStage = ApprovalStatusStage.PENDENTE,
    
    var dateApproved: LocalDateTime? = null,
    
    @Column(columnDefinition = "TEXT")
    var comments: String? = null
)

enum class ApprovalStatusStage {
    PENDENTE,
    APROVADO,
    REPROVADO
}