package com.employee.payroll.controller;

import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.Support;
import com.employee.payroll.entities.model.SupportAnswers;
import com.employee.payroll.service.SupportService;
import com.employee.payroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support")
@CrossOrigin(origins = "*")
public class SupportController {


    @Autowired
    private SupportService supportService;


    @PostMapping("/contact")
    public Support contact(@RequestBody Support support) {

        return supportService.save(support);
    }
//
//    @GetMapping("/contactQuestions")
//    public Support contactQuestions(@RequestBody Support support) {
//
//        return supportService.save(support);
//    }

    @GetMapping("/getAllContactQuestions")
    public List<Support> findAllContactQuestions() {
        return supportService.findAllContactQuestions();
    }


    @PutMapping("/contactAnswers")
    public Support contactAnswers(@RequestBody Support support) {
    support.setAnswerActive(true);
        return supportService.save(support);
    }

    @GetMapping("/findSupport/{employeeId}")
    public List<Support> findSupport(@PathVariable String employeeId) {

        return supportService.findSupportByEmployeeId(employeeId);
    }
}
