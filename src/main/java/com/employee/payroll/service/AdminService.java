package com.employee.payroll.service;


import com.employee.payroll.entities.dto.UserDto;
import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    /* public EmpDetails saveMyUser(EmpDto empdto) {
 EmpDetails empDetails=saveMyUser(empdto);

     return empDetails
     }
 */
    public EmployeeDetails addEmployee(EmployeeDetails employeeDetails) {
        return adminRepository.save(employeeDetails);
    }

    public List<EmployeeDetails> findAllEmployees() {
        return adminRepository.findByIsActive(true);
    }
    public List<EmployeeDetails> findInActiveEmployees() {
        return adminRepository.findByIsActive(false);
    }



    public EmployeeDetails updateEmployee(EmployeeDetails employeeDetails) {
        return adminRepository.save(employeeDetails);

    }

    public EmployeeDetails findEmployee(Long id) {


        return adminRepository.findByIdAndIsActive(id,true);
    }

    public EmployeeDetails findInactiveEmployee(Long id) {


        return adminRepository.findByIdAndIsActive(id,false);
    }


    public EmployeeDetails deleteEmployeeById(Long id) {
        EmployeeDetails emp = this.findEmployee(id);
        emp.setIsActive(false);
        this.adminRepository.save(emp);
        return emp;
    }

    public boolean undoDeleteById(Long id) {
        EmployeeDetails emp = this.findInactiveEmployee(id);
        emp.setIsActive(true);
        EmployeeDetails activatedEmp = adminRepository.save(emp);

        if(activatedEmp.getIsActive())
            return  true;
        return false;
    }


}
