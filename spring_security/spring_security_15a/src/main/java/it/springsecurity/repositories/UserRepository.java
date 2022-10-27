package it.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springsecurity.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findUserByUsername(String username);
}
