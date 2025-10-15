package com.taskmanager.task_manager_backend.repository

import com.taskmanager.task_manager_backend.models.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository : JpaRepository<Document, Long> {
    
    // Para garantir que o código único não seja duplicado, embora já tenhamos a restrição UNIQUE no @Column
    fun findByUniqueCode(uniqueCode: String): Document?
    
    // Método para buscar documentos por projeto (Filtro)
    fun findByProjectId(projectId: Long): List<Document>
}