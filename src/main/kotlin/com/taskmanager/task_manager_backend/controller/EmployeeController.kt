package com.taskmanager.task_manager_backend.controller

import com.taskmanager.task_manager_backend.models.Employee
import com.taskmanager.task_manager_backend.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/employees") 
class EmployeeController(
    private val employeeService: EmployeeService
) {
    // POST /api/employees
    @PostMapping
    fun createEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> {
        val newEmployee = employeeService.saveEmployee(employee)
        return ResponseEntity(newEmployee, HttpStatus.CREATED)
    }

    // GET /api/employees
    @GetMapping
    fun getAllEmployees(): ResponseEntity<List<Employee>> {
        val employees = employeeService.getAllEmployees()
        return ResponseEntity(employees, HttpStatus.OK)
    }

    // GET /api/employees/active
    @GetMapping("/active")
    fun getActiveEmployees(): ResponseEntity<List<Employee>> {
        val employees = employeeService.getActiveEmployees()
        return ResponseEntity(employees, HttpStatus.OK)
    }

    // GET /api/employees/filter?admittedAfter=YYYY-MM-DD
    @GetMapping("/filter")
    fun getEmployeesByFilter(@RequestParam admittedAfter: LocalDate?): ResponseEntity<List<Employee>> {
        if (admittedAfter != null) {
            val employees = employeeService.getEmployeesAdmittedAfter(admittedAfter)
            return ResponseEntity(employees, HttpStatus.OK)
        }
        // Se nenhum filtro for aplicado, retorna todos
        return ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK)
    }
}