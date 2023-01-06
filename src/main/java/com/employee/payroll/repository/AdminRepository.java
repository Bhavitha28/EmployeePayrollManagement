package com.employee.payroll.repository;

import com.employee.payroll.entities.dto.UserDto;
import com.employee.payroll.entities.model.EmployeeDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<EmployeeDetails,Long> {

    EmployeeDetails findByIdAndIsActive(Long id, boolean b);

    List<EmployeeDetails> findByIsActive(boolean b);
}
