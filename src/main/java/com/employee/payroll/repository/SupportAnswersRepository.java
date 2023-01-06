package com.employee.payroll.repository;

import com.employee.payroll.entities.model.Support;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportAnswersRepository extends CrudRepository<Support,Long> {


}