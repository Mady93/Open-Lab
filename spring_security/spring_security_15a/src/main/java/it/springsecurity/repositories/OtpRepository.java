package it.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springsecurity.entities.Otp;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {

	Optional<Otp> findOtpByUsername(String username);
}
