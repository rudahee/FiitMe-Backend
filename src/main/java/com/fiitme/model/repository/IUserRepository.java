package com.fiitme.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fiitme.model.entity.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long>{

	Optional<UserEntity> findByUsername(String username);
	
	Optional<UserEntity> findByUsernameAndPassword(String username, String password);

	Boolean existsUserByEmail(String email);
	
	Boolean existsUserByUsername(String username);
	
}
