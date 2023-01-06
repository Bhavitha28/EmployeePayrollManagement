package com.employee.payroll.controller;


import java.util.ArrayList;
import java.util.List;

import com.employee.payroll.entities.dto.WorkdaysuploadDto;
import com.employee.payroll.service.ExcelToWorkdays;
import com.employee.payroll.entities.dto.ResponseMessage;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import com.employee.payroll.service.EmployeeWorkdaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin("*")
@RequestMapping("/workdays")
@RestController

public class EmployeeWorkdaysController {

    @Autowired
    EmployeeWorkdaysService employeeWorkdaysService;

    @Autowired
    ExcelToWorkdays excelToWorkdays;
    @PostMapping("/upload")
    public WorkdaysuploadDto uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (excelToWorkdays.hasExcelFormat(file)) {
            try {

                return employeeWorkdaysService.saveOrUpdate( excelToWorkdays.excelToworkdays(file.getInputStream()));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            }
        }
        message = "Please upload an excel file!";
        return new WorkdaysuploadDto();
    }
    @GetMapping("/getAllWorkdays")
    public ResponseEntity<List<EmployeeWorkdays>> getAllEmp() {
        try {
            List<EmployeeWorkdays> employeeWorkdays = employeeWorkdaysService.findllempwork();
            if (employeeWorkdays.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employeeWorkdays, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}






