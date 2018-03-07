package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalactica.api.v1.mapper.CustomerMapper;
import bears.beats.battlestargalactica.bootstrapClasses.Bootstrap;
import bears.beats.battlestargalactica.domain.Customer;
import bears.beats.battlestargalactica.repository.CategoryRepository;
import bears.beats.battlestargalactica.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIT {

    private final String updatedName = "UpdatedName";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading customer data");
        System.out.println(customerRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImplementation(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        long id = getCustomerIdValue();

        Customer original = customerRepository.getOne(id);
        assertNotNull(original);

        String originalFirstName = original.getFirstName();
        String originalLastName = original.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updated = customerRepository.findById(id).get();

        assertNotNull(updated);
        assertEquals(updatedName, updated.getFirstName());
        assertThat(originalFirstName, not(equalTo(updated.getFirstName())));
        assertThat(originalLastName, equalTo(updated.getLastName()));
    }

    @Test
    public void patchCustomerUpdateLastName() {
        long id = getCustomerIdValue();

        Customer original = customerRepository.getOne(id);
        assertNotNull(original);

        String originalFirstName = original.getFirstName();
        String originalLastName = original.getFirstName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updated = customerRepository.findById(id).get();

        assertNotNull(updated);
        assertEquals(updatedName, updated.getLastName());
        assertThat(originalFirstName, equalTo(updated.getFirstName()));
        assertThat(originalLastName, not(equalTo(updated.getLastName())));
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers found: " + customers.size());

        return customers.get(0).getId();
    }
}