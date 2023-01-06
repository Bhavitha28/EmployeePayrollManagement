package com.employee.payroll.entities.dto;

import lombok.Data;

@Data
public class SupportDto {
    String issueType;
    String subject;
    String description;
    String employeeId;
}
