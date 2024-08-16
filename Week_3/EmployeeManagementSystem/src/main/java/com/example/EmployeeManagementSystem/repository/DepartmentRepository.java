package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.dto.DepartmentDTO;
import com.example.EmployeeManagementSystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    @Query("SELECT new com.example.EmployeeManagementSystem.dto.DepartmentDTO(d.id, d.name) FROM Department d")
    List<DepartmentDTO> findAllDepartmentDTOs();
}