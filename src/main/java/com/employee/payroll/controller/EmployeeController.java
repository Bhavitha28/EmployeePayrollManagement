package com.employee.payroll.controller;

import com.employee.payroll.entities.dto.EmployeeDTO;
import com.employee.payroll.entities.dto.PayslipsDto;
import com.employee.payroll.entities.dto.WorkDayDto;
import com.employee.payroll.entities.dto.WorkDaysDto;
import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import com.employee.payroll.entities.model.Support;
import com.employee.payroll.entities.model.User;
import com.employee.payroll.service.EmployeeService;
import com.employee.payroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/Employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;




//    @GetMapping("getDetails/{id}")
//    public EmployeeDTO getEmployeeById(@PathVariable("id") String id){
//        EmployeeDetails employee = employeeService.getEmployeeByEmployeeId(id);
//        EmployeeDTO employeeDto = new EmployeeDTO();
//        employeeDto.setEmployeeId(employee.getEmployeeId());
//        employeeDto.setEmployeeDoj(employee.getEmployeeDoj());
//        employeeDto.setEmployeeName(employee.getEmployeeName());
//        employeeDto.setEmployeeType(employee.getEmployeeType());
//        //set data from entity to dto
//        return employeeDto;
//
//    }


    @GetMapping("/getOneEmployee/{employeeId}")
    public EmployeeDetails findEmployee(@PathVariable("employeeId") String employeeId) {

        return employeeService.getEmployeeByEmployeeId(employeeId);
    }


    @GetMapping("get-payslips/{id}")
    public ResponseEntity<PayslipsDto> getEmployeeById(@RequestParam Long timefrom, @RequestParam Long timeto,@PathVariable("id") String id){
        EmployeeDetails emp = employeeService.getEmployeeByEmployeeId(id);
        List<EmployeeWorkdays> workdays =  employeeService.getWorkDays(emp,timefrom,timeto);

        WorkDaysDto workdaysDto = new WorkDaysDto();
        workdays.forEach(workday->{
            WorkDayDto wd = new WorkDayDto();
            wd.setId(workday.getId());
            wd.setTimeFrom(workday.getTimeFrom());
            wd.setTimeTo(workday.getTimeTo());
            wd.setLeaveBal(workday.getLeaveBal());
            wd.setLeavesApplied(workday.getLeavesApplied());
            wd.setSalary(workday.getEmployeeId().getGrossSalary().doubleValue());
            workdaysDto.getWorkDays().add(wd);
        });

        String payrollUrl = "http://localhost:8082/admin/payrolls";
        HttpEntity<WorkDaysDto> request = new HttpEntity<>(workdaysDto);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate
                .exchange(payrollUrl, HttpMethod.POST, request , PayslipsDto.class);

    }



}