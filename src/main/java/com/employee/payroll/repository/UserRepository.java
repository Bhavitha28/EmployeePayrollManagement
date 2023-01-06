package com.employee.payroll.repository;

import com.employee.payroll.entities.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


    User findByUserNameAndPassword(String username, String password);

    User findByUserName(String username);
}
