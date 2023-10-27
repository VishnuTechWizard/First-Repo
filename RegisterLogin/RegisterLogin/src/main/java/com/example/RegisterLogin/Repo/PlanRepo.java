package com.example.RegisterLogin.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.RegisterLogin.Entity.Plans;



@Repository
public interface PlanRepo  extends MongoRepository<Plans ,Integer> {

	boolean existsByUsername(String username);

}
