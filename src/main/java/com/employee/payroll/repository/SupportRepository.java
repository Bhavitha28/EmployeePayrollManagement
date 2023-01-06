package com.employee.payroll.repository;


import com.employee.payroll.entities.model.Support;
import com.employee.payroll.entities.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends CrudRepository<Support,Long> {

public List<Support> findByEmployeeId(String employeeId);

    List<Support> findByAnswerActive(boolean b);
}
