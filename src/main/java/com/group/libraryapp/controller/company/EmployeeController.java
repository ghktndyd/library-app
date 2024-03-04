package com.group.libraryapp.controller.company;

import com.group.libraryapp.domain.company.Employee;
import com.group.libraryapp.dto.company.EmployeeRequest;
import com.group.libraryapp.dto.company.EmployeeResponse;
import com.group.libraryapp.service.company.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeRequest request) {
        Employee employee = employeeService.registerEmployee(request);

        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employeesInfo = employeeService.getAllEmployeesInfo();
        return ResponseEntity.ok(employeesInfo);
    }
}
