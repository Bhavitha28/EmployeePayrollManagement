package com.employee.payroll.entities.dto;

import lombok.Data;

@Data
public class PayslipDto {
    private Long id;

    private Long empWorkId;

    private Float epf;

    private Float tax;

    private Float leaveDeduction;

    private Float his;

    private  Float netpay;

    private Float hra;

    private Float basic;

}
