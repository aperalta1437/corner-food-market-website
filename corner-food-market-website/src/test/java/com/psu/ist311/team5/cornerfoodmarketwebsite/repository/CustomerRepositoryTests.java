package com.psu.ist311.team5.cornerfoodmarketwebsite.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("aAmiguelp007@gmail.com");
        customer.setPassword("P@ssword1");
        customer.setFirstName("Angel");
        customer.setLastName("Peralta");

        Customer savedCustomer = repo.save(customer);

        Customer existingCustomer = entityManager.find(Customer.class, savedCustomer.getId());

        assertThat(existingCustomer.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    public void testFindCustomerByEmail() {
        String email = "amiguelp007@gmail.com";

        Customer customer = repo.findByEmail(email);

        assertThat(customer).isNotNull();


    }
}
