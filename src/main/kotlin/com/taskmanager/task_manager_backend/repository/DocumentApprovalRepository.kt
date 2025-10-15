package com.taskmanager.task_manager_backend.repository

import com.taskmanager.task_manager_backend.models.DocumentApproval
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.taskmanager.task_manager_backend.models.ApprovalStatusStage

@Repository
interface DocumentApprovalRepository : JpaRepository<DocumentApproval, Long> {
    
    // Método para buscar o histórico de aprovações para uma versão específica
    fun findByDocumentVersionIdOrderByStageAsc(documentVersionId: Long): List<DocumentApproval>

    // Método para verificar se há alguma etapa pendente para a versão
    fun existsByDocumentVersionIdAndStatus(documentVersionId: Long, status: ApprovalStatusStage): Boolean
}