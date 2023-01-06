package com.employee.payroll.service;

import com.employee.payroll.entities.dto.WorkDayDto;
import com.employee.payroll.entities.dto.WorkDaysDto;
import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import com.employee.payroll.entities.model.Support;
import com.employee.payroll.entities.model.User;
import com.employee.payroll.repository.EmployeeDetailsRepository;
import com.employee.payroll.repository.EmployeePayslipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;
    @Autowired
    private EmployeePayslipRepository employeePayslipRepository;

    public EmployeeDetails getEmployeeByEmployeeId(String empId){
        return employeeDetailsRepository.findByEmployeeIdAndIsActive(empId,true);
    }

    public List<EmployeeWorkdays> getWorkDays(EmployeeDetails employee, Long timefrom, Long timeto){
        Timestamp timeF = new Timestamp(timefrom);
        Timestamp timeT = new Timestamp(timeto);
        Date timeFrom = new Date(timeF.getTime());
        //timeFrom.setTime(0l);
        timeFrom.setHours(0);
        timeFrom.setMinutes(0);
        timeFrom.setSeconds(0);
        Date timeTo = new Date(timeT.getTime());
        return employeePayslipRepository.findByTimeFromAndTimeToAndEmployeeId(timeFrom,timeTo,employee);

    }


}
