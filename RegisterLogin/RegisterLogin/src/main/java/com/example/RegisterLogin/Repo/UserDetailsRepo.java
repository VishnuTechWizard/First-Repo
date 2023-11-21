package com.example.RegisterLogin.Repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.RegisterLogin.Entity.UserDetails;

public interface UserDetailsRepo extends MongoRepository<UserDetails ,String>  {

	Optional<UserDetails> findById(String id);


}
