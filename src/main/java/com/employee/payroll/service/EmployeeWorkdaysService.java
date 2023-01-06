package com.employee.payroll.service;

import com.employee.payroll.entities.dto.WorkDayDto;
import com.employee.payroll.entities.dto.WorkDaysDto;
import com.employee.payroll.entities.dto.WorkdaysuploadDto;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import com.employee.payroll.repository.EmployeeWorkdaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EmployeeWorkdaysService {

    @Autowired
    EmployeeWorkdaysRepository employeeWorkdaysRepository;

//get by emp id.
//save or update all saveAll(List<Empwork> workdays)
//delete

    public List<EmployeeWorkdays> findllempwork() {
        ZoneId z = ZoneId.of( "UTC" ) ;
        TimeZone.setDefault(TimeZone.getTimeZone(z));
        return employeeWorkdaysRepository.findAll();
    }


    public EmployeeWorkdays findemployee(Long id) {

        return employeeWorkdaysRepository.findByEmployeeIdAndIsActive(id,true);
    }

    public WorkdaysuploadDto saveOrUpdate(List<EmployeeWorkdays> employeeWorkdays){
        WorkdaysuploadDto response = new WorkdaysuploadDto();
        AtomicReference<Integer> excelIndex= new AtomicReference<>(0);
        employeeWorkdays.forEach((workday)->{
            excelIndex.getAndSet(excelIndex.get() + 1);
            List<EmployeeWorkdays> existingworkday = employeeWorkdaysRepository.findByEmployeeIdAndIsActiveAndTimeFromAndTimeTo(workday.getEmployeeId(),true,workday.getTimeFrom(),workday.getTimeTo());
            if(workday.getEmployeeId()==null){
                response.getErrorMsg().add(excelIndex+"Employee does not exist");
            }else if(existingworkday.isEmpty()){
                try {
                    employeeWorkdaysRepository.save(workday);
                }
                catch (Exception e){
                    response.getErrorMsg().add(workday.getEmployeeId().getEmployeeId() +" Employee does not exist");
                }
            } else{
                response.getErrorMsg().add(workday.getEmployeeId().getEmployeeId() +" has redundant entry");
            }
        });
        return  response;
    }

    public EmployeeWorkdays deleteEmployeeById(Long id) {
        EmployeeWorkdays employee = this.findemployee(id);
        employee.setActive(false);
        this.employeeWorkdaysRepository.save(employee);
        return employee;
    }

    public List<EmployeeWorkdays> getWorkDays(Long timefrom, Long timeto){

        Timestamp timeF = new Timestamp(timefrom);
        Timestamp timeT = new Timestamp(timeto);
        Date timeFrom = new Date(timeF.getTime());
        Date timeTo = new Date(timeT.getTime());
        List<EmployeeWorkdays> workdays= employeeWorkdaysRepository.findByTimeFromAndTimeTo(timeFrom,timeTo);
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
        String payrollUrl = "http://localhost:8082/admin/generate-payroll";
        HttpEntity<WorkDaysDto> request = new HttpEntity<>(workdaysDto);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
                .exchange(payrollUrl, HttpMethod.POST, request , WorkDaysDto.class);

        return employeeWorkdaysRepository.findByTimeFromAndTimeTo(timeFrom,timeTo);
    }


}
