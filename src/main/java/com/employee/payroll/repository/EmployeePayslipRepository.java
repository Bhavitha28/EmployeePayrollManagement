package com.employee.payroll.repository;

import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeePayslipRepository extends JpaRepository<EmployeeWorkdays, Long> {
    @Query("select d from EmployeeWorkdays d where d.timeFrom>=?1 and d.timeTo<=?2")
    List<EmployeeWorkdays> findByTimeFromAndTimeTo(Date timeFrom, Date timeTo);

    @Query("select d from EmployeeWorkdays d where d.timeFrom>=?1 and d.timeTo<=?2 and d.employeeId = ?3")
    List<EmployeeWorkdays> findByTimeFromAndTimeToAndEmployeeId(Date timeFrom, Date timeTo, EmployeeDetails emp);

}
