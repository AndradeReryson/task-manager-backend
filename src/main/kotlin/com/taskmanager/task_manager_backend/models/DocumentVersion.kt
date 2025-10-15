package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "document_versions")
data class DocumentVersion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    // Chave Estrangeira para o Documento Mestre
    @Column(nullable = false)
    var documentId: Long, 

    @Column(nullable = false)
    var versionNumber: String, // Ex: "A", "01", "02"

    @Column(nullable = false)
    var filename: String, // Nome do arquivo original (Ex: "Desenho_E_001_A.pdf")
    
    @Column(nullable = false)
    var filePath: String, // Caminho no servidor/storage (futuramente S3)

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus = ApprovalStatus.EM_APROVACAO, // Status atual da versão

    @Column(nullable = false)
    var createdByEmployeeId: Long,
    
    @Column(nullable = false)
    var creationDate: LocalDateTime = LocalDateTime.now()
)

enum class ApprovalStatus {
    EM_APROVACAO,
    REPROVADO,
    APROVADO,
    OBSERVACAO
}