package com.dish.repository;

import java.util.List;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;

import com.dish.model.Customer;
import com.dish.model.EmailAddress;

public interface CustomerRepository extends GemfireRepository<Customer, Long> {

	/**
	 * Returns all {@link Customer}s.
	 *
	 * @return
	 */
	List<Customer> findAll();

	/**
	 * Finds all {@link Customer}s with the given lastname.
	 *
	 * @param lastname
	 * @return
	 */
	List<Customer> findByLastname(String lastname);

	/**
	 * Finds the Customer with the given {@link EmailAddress}.
	 *
	 * @param emailAddress
	 * @return
	 */
	@Query("SELECT * FROM /Customer x where x.emailAddress = $1")
	Customer findByEmailAddress(EmailAddress emailAddress);
	

}