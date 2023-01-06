package com.employee.payroll.controller;

import com.employee.payroll.entities.dto.WorkDayDto;
import com.employee.payroll.entities.dto.WorkDaysDto;
import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import com.employee.payroll.service.EmployeeService;
import com.employee.payroll.service.EmployeeWorkdaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")

public class PayslipController {

    @Autowired
    EmployeeWorkdaysService employeeWorkdaysService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/createPayslips")
    public void createPayslips(@RequestParam("timefrom") Long timefrom, @RequestParam("timeto") Long timeto){

        List<EmployeeWorkdays> workdays =  employeeWorkdaysService.getWorkDays(timefrom,timeto);
    }

}
