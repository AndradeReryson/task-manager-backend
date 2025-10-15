package com.taskmanager.task_manager_backend.repository

import com.taskmanager.task_manager_backend.models.DocumentVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.taskmanager.task_manager_backend.models.ApprovalStatus

@Repository
interface DocumentVersionRepository : JpaRepository<DocumentVersion, Long> {
    
    // Método para buscar todas as versões de um documento mestre
    fun findByDocumentIdOrderByVersionNumberDesc(documentId: Long): List<DocumentVersion>

    // Método para encontrar a versão que tem um status de aprovação específico
    fun findByDocumentIdAndApprovalStatus(documentId: Long, status: ApprovalStatus): List<DocumentVersion>
}