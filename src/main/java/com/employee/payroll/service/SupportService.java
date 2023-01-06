package com.employee.payroll.service;

import com.employee.payroll.entities.model.EmployeeWorkdays;
import com.employee.payroll.entities.model.Support;
import com.employee.payroll.entities.model.User;
import com.employee.payroll.repository.SupportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@Service
public class SupportService {

    @Autowired
    private SupportRepository supportRepository;


    public Support save(Support support) {

        return supportRepository.save(support);
    }

public List<Support> findSupportByEmployeeId(String employeeId){
        return supportRepository.findByEmployeeId(employeeId);
}


    public List<Support> findAllContactQuestions() {
        return (List<Support>) supportRepository.findByAnswerActive(false);
    }

}
