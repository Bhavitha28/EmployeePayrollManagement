package com.employee.payroll.entities.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class WorkDayDto {
    private Long id;

    private Long employeeId;

    private Integer totalDays;

    private Float leaveBal;

    private Float leavesApplied;

    private Date timeFrom;

    private Date timeTo;

    public LocalDateTime createdOn;

    private LocalDateTime lastModifiedOn;

    private Double salary;
}
