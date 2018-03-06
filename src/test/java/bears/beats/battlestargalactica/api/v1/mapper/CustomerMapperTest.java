package bears.beats.battlestargalactica.api.v1.mapper;

import bears.beats.battlestargalactica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalactica.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private static final String FIRSTNAME = "Dexter";
    private static final String LASTNAME = "Morgan";
    private static final Long ID = 1L;
    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerMapper() {

        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);
        customer.setId(ID);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
    }
}
