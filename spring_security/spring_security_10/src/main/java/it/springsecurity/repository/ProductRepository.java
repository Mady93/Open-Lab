package it.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springsecurity.users.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
