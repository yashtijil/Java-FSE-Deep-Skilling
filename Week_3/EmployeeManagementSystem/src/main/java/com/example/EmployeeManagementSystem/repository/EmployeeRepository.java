package com.example.EmployeeManagementSystem.repository;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query method - method naming conventions
    List<Employee> findByName(String name);

    // Custom query method - @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.email = ?1")

    Employee findByEmail(String email);

    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT new com.example.EmployeeManagementSystem.dto.EmployeeDTO(e.id, e.name, e.email) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();

}