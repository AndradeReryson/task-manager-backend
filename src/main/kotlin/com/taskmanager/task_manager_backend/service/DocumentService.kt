package com.taskmanager.task_manager_backend.service

import com.taskmanager.task_manager_backend.models.*
import com.taskmanager.task_manager_backend.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class DocumentService(
    private val documentRepository: DocumentRepository,
    private val versionRepository: DocumentVersionRepository,
    private val approvalRepository: DocumentApprovalRepository
) {
    
    /**
     * Lógica principal: Cria um novo Documento Mestre e sua primeira Versão.
     * @param document O Documento a ser criado (sem o ID e currentVersionId)
     * @param initialVersion Os detalhes da primeira versão (incluindo file path e createdBy)
     * @param approverIds Uma lista de IDs de Colaboradores para as 3 etapas de aprovação
     */
    @Transactional // Garante que, se qualquer parte falhar, tudo é desfeito (rollback)
    fun createNewDocument(
        document: Document,
        initialVersion: DocumentVersion,
        approverIds: List<Long> // Esperamos 3 IDs para as 3 etapas
    ): Document {
        
        // 1. GARANTIR CÓDIGO ÚNICO (OBRA-DISC-SEQ-REV)
        // Lógica de geração de código (Simplificada aqui, mas essencial)
        // Exemplo: document.uniqueCode = generateUniqueCode(document)
        // O front-end ou uma lógica interna deve garantir o sequencial correto.

        val savedDocument = documentRepository.save(document)

        // 2. SALVAR A PRIMEIRA VERSÃO
        initialVersion.documentId = savedDocument.id!! // Associa a versão ao Documento Mestre
        initialVersion.versionNumber = "A" // Define a primeira revisão
        val savedVersion = versionRepository.save(initialVersion)

        // 3. ATUALIZAR O DOCUMENTO MESTRE COM A VERSÃO ATUAL
        savedDocument.currentDocumentVersionId = savedVersion.id
        documentRepository.save(savedDocument)

        // 4. CRIAR O FLUXO DE APROVAÇÃO (3 ESTÁGIOS)
        if (approverIds.size >= 3) {
            approverIds.take(3).forEachIndexed { index, approverId ->
                val approvalStage = DocumentApproval(
                    documentVersionId = savedVersion.id!!,
                    stage = index + 1, // Estágio 1, 2, 3
                    approverEmployeeId = approverId,
                    status = ApprovalStatusStage.PENDENTE
                )
                approvalRepository.save(approvalStage)
            }
        }
        
        return savedDocument
    }

    fun findDocumentsByProjectId(projectId: Long): List<Document> {
        return documentRepository.findByProjectId(projectId)
    }

    fun findVersionsByDocumentId(documentId: Long): List<DocumentVersion> {
        return versionRepository.findByDocumentIdOrderByVersionNumberDesc(documentId)
    }

    @Transactional
    fun handleDocumentApproval(approvalId: Long, request: ApprovalRequest): DocumentApproval? {
        val approvalStage = approvalRepository.findById(approvalId).orElse(null)
            ?: return null

        if (approvalStage.approverEmployeeId != request.approverEmployeeId) {
            throw SecurityException("Colaborador não autorizado para esta etapa de aprovação.")
        }

        if (approvalStage.status != ApprovalStatusStage.PENDENTE) {
            throw IllegalStateException("Esta etapa já foi concluída.")
        }

        approvalStage.status = if (request.isApproved) ApprovalStatusStage.APROVADO else ApprovalStatusStage.REPROVADO
        approvalStage.dateApproved = LocalDateTime.now()
        approvalStage.comments = request.comments

        val updatedApproval = approvalRepository.save(approvalStage)

        // 4. LÓGICA DE FLUXO E VERSÃO (Crucial!)
        val version = versionRepository.findById(approvalStage.documentVersionId).get()

        if (approvalStage.status == ApprovalStatusStage.REPROVADO) {
            // Se REPROVADO, marca a versão como REPROVADA e encerra o fluxo
            version.approvalStatus = ApprovalStatus.REPROVADO
            versionRepository.save(version)
        } else {
            // Se APROVADO, verifica se é a última etapa
            val isLastStage = approvalRepository.existsByDocumentVersionIdAndStatus(
                approvalStage.documentVersionId, ApprovalStatusStage.PENDENTE
            ).not() // Verifica se NÃO existe mais nenhuma PENDENTE

            if (isLastStage) {
                // Se for a última etapa, marca a versão como APROVADA
                version.approvalStatus = ApprovalStatus.APROVADO
                versionRepository.save(version)
            }
        }

        return updatedApproval
    }
    
    // --- LÓGICA DE GESTÃO DE VERSÕES E APROVAÇÃO (Para implementar depois) ---

    // 5. Funcionalidade para Subir Nova Versão (Lógica complexa de incremento de versão)
    // fun uploadNewVersion(documentId: Long, newFileVersion: DocumentVersion): DocumentVersion { ... }

    // 6. Funcionalidade para Aprovar Etapa
    // fun approveStage(approvalId: Long, approverId: Long): DocumentApproval { ... }
}

data class ApprovalRequest(
    val approverEmployeeId: Long, 
    val comments: String? = null,
    val isApproved: Boolean
)