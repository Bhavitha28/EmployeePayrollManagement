package com.employee.payroll.repository;

import com.employee.payroll.entities.model.EmployeeDetails;
import com.employee.payroll.entities.model.EmployeeWorkdays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeWorkdaysRepository extends JpaRepository<EmployeeWorkdays, Long> {


    EmployeeWorkdays findByEmployeeIdAndIsActive(Long id, boolean b);
    @Query("select d from EmployeeWorkdays d where d.timeFrom>=?1 and d.timeTo<=?2")
    List<EmployeeWorkdays> findByTimeFromAndTimeTo(Date timeFrom, Date timeTo);


    List<EmployeeWorkdays> findByEmployeeIdAndIsActiveAndTimeFromAndTimeTo(EmployeeDetails employeeId, boolean b, Date timeFrom, Date timeTo);
}
