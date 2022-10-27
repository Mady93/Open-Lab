package it.mobileapp.auth.repository.data;

import java.util.Optional;

import it.mobileapp.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserEntityRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByUsername(String u);
}
