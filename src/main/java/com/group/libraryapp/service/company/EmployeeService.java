package com.group.libraryapp.service.company;

import com.group.libraryapp.domain.company.Employee;
import com.group.libraryapp.domain.company.Team;
import com.group.libraryapp.dto.company.EmployeeRequest;
import com.group.libraryapp.dto.company.EmployeeResponse;
import com.group.libraryapp.repository.company.EmployeeRepository;
import com.group.libraryapp.repository.company.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public Employee registerEmployee(EmployeeRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(IllegalArgumentException::new);

        Employee employee = Employee.builder()
                .name(request.getName())
                .isManager(request.isManager())
                .entryDate(request.getEntryDate())
                .birthDay(request.getBirthDay())
                .team(team)
                .build();

        return employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getAllEmployeesInfo() {
        return employeeRepository.findAllEmployeeInfo();
    }
}
