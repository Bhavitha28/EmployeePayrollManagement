package com.employee.payroll.repository;

import com.employee.payroll.entities.model.EmployeeDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends CrudRepository<EmployeeDetails,Long> {
    EmployeeDetails findByEmployeeIdAndIsActive(String empId, boolean b);
}
