package com.employee.payroll.entities.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class WorkDaysDto {
    private List<WorkDayDto> workDays = new ArrayList<>();

}
