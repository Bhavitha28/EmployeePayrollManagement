package com.employee.payroll.entities.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="employee_workdays")
public class EmployeeWorkdays {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @OneToOne
    @JoinColumn(name="EMPLOYEE_ID")
    private EmployeeDetails employeeId;

    @Column(name="TOTAL_DAYS")
    private Integer totalDays;

    @Column(name="LEAVE_BAL")
    private Float leaveBal;


    @Column(name="LEAVES_APPLIED")
    private Float leavesApplied;

    @Column(name="TIME_FROM")
    private Date timeFrom;

    @Column(name="TIME_TO")
    private Date timeTo;

    @Column(name="is_active")
    private Boolean isActive=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDetails getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(EmployeeDetails employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Float getLeaveBal() {
        return leaveBal;
    }

    public void setLeaveBal(Float leaveBal) {
        this.leaveBal = leaveBal;
    }

    public Float getLeavesApplied() {
        return leavesApplied;
    }

    public void setLeavesApplied(Float leavesApplied) {
        this.leavesApplied = leavesApplied;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    @Column(name="created_on")
    public LocalDateTime createdOn;

    @Column(name="last_modified_on")
    private LocalDateTime lastModifiedOn;



    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn( ) {
        this.createdOn = LocalDateTime.now();
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    @PreUpdate
    public void setLastModifiedOn() {
        this.lastModifiedOn = LocalDateTime.now();
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


//    public void setEmpId(String empId) {
//    }
}
