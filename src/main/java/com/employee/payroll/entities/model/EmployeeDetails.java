package com.employee.payroll.entities.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="employee_details")
public class EmployeeDetails extends BaseEntity {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        @Column(name="ID")
        private Long id;

        @Column(name="EMPLOYEE_NAME")
        private String employeeName;

        @Column(name="EMPLOYEE_ID")
        private String employeeId;

        @Column(name="EMPLOYEE_DOJ")
        @JsonFormat(pattern="yyyy-MM-dd")
        private Date employeeDoj;

        @Column(name="EMPLOYEE_TYPE")
        private  String employeeType;

        @Column(name="GROSS_SALARY")
        private Float grossSalary;
        @Column(name="IS_ACTIVE")
        private Boolean isActive=true;

@Column(name="EMAIL_Id")
private String mailId;
        @Column(name="CREATED_ON")
        public LocalDateTime createdOn;

        @Column(name="LAST_MODIFIED_ON")
        private LocalDateTime lastModifiedOn;

        public Boolean getActive() {
                return isActive;
        }

        public void setActive(Boolean active) {
                isActive = active;
        }

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

}
