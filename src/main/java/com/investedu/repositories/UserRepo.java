package com.investedu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.investedu.model.Users;


public interface UserRepo extends CrudRepository<Users, Integer> {}
