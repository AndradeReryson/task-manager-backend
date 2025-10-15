package com.taskmanager.task_manager_backend.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "documents")
data class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    // CÓDIGO ÚNICO: OBRA-DISC-SEQ-REV (Ex: SEDA-EST-0001-A)
    @Column(nullable = false, unique = true)
    var uniqueCode: String,

    @Column(nullable = false)
    var title: String,

    // Relaciona com a versão atualmente ativa (One-to-One será feito mais tarde no Repository)
    var currentDocumentVersionId: Long? = null,
    
    // CAMPOS DE FILTRO
    var category: String? = null, // Ex: Desenho, Relatório, Especificação
    var discipline: String? = null, // Ex: Estruturas, Arquitetura
    
    var tags: String? = null, // Tags separadas por vírgula

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    
    // Qual projeto este documento pertence
    var projectId: Long
)