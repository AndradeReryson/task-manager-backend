package com.taskmanager.task_manager_backend.service

import com.taskmanager.task_manager_backend.models.Employee
import com.taskmanager.task_manager_backend.repository.EmployeeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
    fun saveEmployee(employee: Employee): Employee {
        // Futura lógica de validação: garantir que o email seja único, etc.
        return employeeRepository.save(employee)
    }

    fun getAllEmployees(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun getActiveEmployees(): List<Employee> {
        return employeeRepository.findByIsActiveTrueOrderByName()
    }

    fun getEmployeesAdmittedAfter(date: LocalDate): List<Employee> {
        return employeeRepository.findByAdmissionDateAfter(date)
    }

    fun getEmployeeById(id: Long): Employee? {
        return employeeRepository.findById(id).orElse(null)
    }
}