
package com.employee.payroll.entities.dto;

import lombok.Data;

@Data
public class UserDto extends LoginDto{
    String employeeId;
    String type;
    String employeeName;
Long mobileNumber;
}

