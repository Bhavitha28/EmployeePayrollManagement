
package com.employee.payroll.entities.model;


;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User extends BaseEntity {


//public class User implements UserDetails {
   @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="EMPLOYEE_ID")
    private String employeeId;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="USER_TYPE")
    private String userType;

    @Column(name="EMPLOYEE_NAME")
 private  String employeeName;

 @Column(name="MOBILE_NUMBER")
 private String mobileNumber;

}

