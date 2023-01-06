package com.employee.payroll.entities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="support")
public class Support {

        //public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        @Column(name="ID")
        private Long id;

        @Column(name="ISSUE_TYPE")
        private String issueType;

        @Column(name="SUBJECT")
        private String subject;

        @Column(name="DESCRIPTION")
        private String description;

        @Column(name="EMPLOYEE_ID")
        private String employeeId;

        @Column(name="ANSWER_ACTIVE")
        private boolean answerActive=false;

        @OneToOne(targetEntity = SupportAnswers.class,cascade = CascadeType.ALL)
        @JoinColumn(name="sup_supAns_fk" ,referencedColumnName = "id")
        private SupportAnswers supportAnswers;


    }



