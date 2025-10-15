package com.taskmanager.task_manager_backend.controller

import com.taskmanager.task_manager_backend.models.*
import com.taskmanager.task_manager_backend.service.DocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.taskmanager.task_manager_backend.service.ApprovalRequest

data class DocumentCreationRequest(
    val documentDetails: Document,
    val versionDetails: DocumentVersion,
    val approverIds: List<Long>
)

@RestController
@RequestMapping("/api/documents")
class DocumentController(
    private val documentService: DocumentService
) {

    // POST /api/documents
    @PostMapping
    fun createDocument(@RequestBody request: DocumentCreationRequest): ResponseEntity<Document> {
        val newDocument = documentService.createNewDocument(
            request.documentDetails,
            request.versionDetails,
            request.approverIds
        )
        return ResponseEntity(newDocument, HttpStatus.CREATED)
    }

    // PUT /api/documents/approval/{approvalId}
    @PutMapping("/approval/{approvalId}")
    fun handleApproval(
        @PathVariable approvalId: Long,
        @RequestBody request: ApprovalRequest
    ): ResponseEntity<DocumentApproval> {
        
        return try {
            val updatedApproval = documentService.handleDocumentApproval(approvalId, request)
            if (updatedApproval != null) {
                ResponseEntity(updatedApproval, HttpStatus.OK)
            } else {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        } catch (e: SecurityException) {
            ResponseEntity(HttpStatus.FORBIDDEN) // 403 Proibido
        } catch (e: IllegalStateException) {
            ResponseEntity(HttpStatus.CONFLICT) // 409 Conflito (já concluído)
        }
    }

    // GET /api/documents/project/{projectId}
    @GetMapping("/project/{projectId}")
    fun getDocumentsByProjectId(@PathVariable projectId: Long): ResponseEntity<List<Document>> {
        val documents = documentService.findDocumentsByProjectId(projectId) 
        return ResponseEntity(documents, HttpStatus.OK)
    }
    
    // GET /api/documents/{documentId}/versions
    @GetMapping("/{documentId}/versions")
    fun getDocumentVersions(@PathVariable documentId: Long): ResponseEntity<List<DocumentVersion>> {
        val versions = documentService.findVersionsByDocumentId(documentId)
        return ResponseEntity(versions, HttpStatus.OK)
    }
}