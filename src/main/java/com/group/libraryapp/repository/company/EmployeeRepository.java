package com.group.libraryapp.repository.company;

import com.group.libraryapp.domain.company.Employee;
import com.group.libraryapp.dto.company.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.group.libraryapp.dto.company.EmployeeResponse(e.name, t.name, " +
            "CASE WHEN e.isManager = true THEN 'MANAGER' ELSE 'MEMBER' END, " +
            "e.birthDay, e.entryDate) " +
            "FROM Employee e LEFT JOIN e.team t")
    List<EmployeeResponse> findAllEmployeeInfo();

}
