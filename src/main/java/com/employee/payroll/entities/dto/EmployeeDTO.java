package com.employee.payroll.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class EmployeeDTO {

    private String employeeName;


    private String employeeId;

    private Date employeeDoj;


    private  String employeeType;
}
