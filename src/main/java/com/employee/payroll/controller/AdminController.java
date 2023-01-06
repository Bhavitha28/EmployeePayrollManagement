package com.employee.payroll.controller;

import com.employee.payroll.entities.dto.UserDto;
import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/addEmployee")
    // @PreAuthorize("hasPermission(#employeeDetails,'admin')")
    public EmployeeDetails addEmployee(@RequestBody EmployeeDetails employeeDetails) {
        return adminService.addEmployee(employeeDetails);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeDetails> findAllEmployees() {
        return adminService.findAllEmployees();
    }

    @GetMapping("/getInActiveEmployees")
    public List<EmployeeDetails> findInActiveEmployees() {
        return adminService.findInActiveEmployees();
    }


    @GetMapping("/getOneEmployee/{id}")
    public EmployeeDetails findEmployee(@PathVariable("id") Long id) {

        return adminService.findEmployee(id);
    }

    @PutMapping("/updateEmployee")
    public EmployeeDetails updateEmployee(@RequestBody EmployeeDetails employeeDetails) {
        return adminService.updateEmployee(employeeDetails);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public EmployeeDetails deleteEmp(@PathVariable("id") Long id) {
        return adminService.deleteEmployeeById(id);
    }


    @GetMapping("/undoDelete/{id}")
    public boolean undoDelete(@PathVariable("id") Long id) {
        return adminService.undoDeleteById(id);
    }

}
