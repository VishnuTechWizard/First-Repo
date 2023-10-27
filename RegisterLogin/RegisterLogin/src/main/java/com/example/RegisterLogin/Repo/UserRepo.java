package com.example.RegisterLogin.Repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Entity.User;

@Repository
public interface UserRepo extends MongoRepository<User ,Integer> {

	Optional<User> findOneByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);


//	User findByUsername(String username);


}


