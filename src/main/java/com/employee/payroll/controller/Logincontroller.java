package com.employee.payroll.controller;

import com.employee.payroll.entities.dto.LoginDto;
import com.employee.payroll.entities.dto.UserDto;
import com.employee.payroll.entities.model.User;
import com.employee.payroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class Logincontroller {

    @Autowired
    private UserService userService;


    @PostMapping("/Signup")
    public User signup(@RequestBody User user) {

        return userService.save(user);
    }

//    @PostMapping("/role")
//    public RoleEntity createNewRole(RoleEntity role){
//        return roleService.createNewRole(role);
//    }
//
//    @PostConstruct
//    public void initRolesAndUsers(){
//        userService.initRolesAndUser();
//    }

    /*public String hello()
    {
        User user=new User();
        user.setUserName("bhavi");
        user.setEmpId("sg1928");
        user.setPassword("Chinni");
        user.setUserType("Emp");
        userService.save(user);
        return "welcome";
    }*/

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        User user = userService.login(loginDto.getUsername(), loginDto.getPassword());
        UserDto userDto = new UserDto();
        userDto.setType(user.getUserType());
        userDto.setEmployeeId(user.getEmployeeId());
        return userDto;
    }

    @GetMapping("/getByUsername/{username}")
    public User getByUsername(@PathVariable String username) {

        return userService.getByUsername(username);
    }


    @PutMapping("/updateAdmin")
    public User updateAdmin(@RequestBody User user) {
        return userService.updateAdmin(user);
    }
}