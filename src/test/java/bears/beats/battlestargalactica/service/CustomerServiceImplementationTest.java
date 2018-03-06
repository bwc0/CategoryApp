package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalactica.api.v1.mapper.CustomerMapper;
import bears.beats.battlestargalactica.domain.Customer;
import bears.beats.battlestargalactica.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplementationTest {

    private static final Long ID = 1L;
    private static final String FIRSTNAME = "Dexter";
    private static final String LASTNAME = "Morgan";
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImplementation(CustomerMapper.INSTANCE,
                customerRepository);
    }

    @Test
    public void getAllCustomersTest() {

        List<Customer> customers  = Arrays.asList(new Customer(), new Customer(),
                new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(customers.size(), customerDTOS.size());
    }

    @Test
    public void getCustomersByIdTest() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);

        Optional<Customer> customerOptional = Optional.of(customer);

        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        CustomerDTO customerDTO = customerService.getCustomersById(1L);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
    }
}