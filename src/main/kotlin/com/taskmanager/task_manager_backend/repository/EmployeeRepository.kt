package com.taskmanager.task_manager_backend.repository

import com.taskmanager.task_manager_backend.models.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {

    fun findByAdmissionDateAfter(date: LocalDate): List<Employee>

    fun findByIsActiveTrueOrderByName(): List<Employee>
}