package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalactica.api.v1.mapper.CustomerMapper;
import bears.beats.battlestargalactica.domain.Customer;
import bears.beats.battlestargalactica.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImplementation implements CustomerService{

    public static final String API_V1_CUSTOMER = "/api/v1/customer/";
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImplementation(CustomerMapper customerMapper,
                                         CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(API_V1_CUSTOMER + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomersById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {
                    if(customerDTO.getFirstName() != null) {
                        customer.setFirstName(customerDTO.getFirstName());
                    }

                    if (customerDTO.getLastName() != null) {
                        customer.setLastName(customerDTO.getLastName());
                    }

                    CustomerDTO returnDTO = customerMapper
                            .customerToCustomerDTO(customerRepository.save(customer));

                    returnDTO.setCustomerUrl(API_V1_CUSTOMER + id);

                    return returnDTO;
                }).orElseThrow(RuntimeException::new);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository
                .save(customer);

        CustomerDTO returnDTO = customerMapper
                .customerToCustomerDTO(savedCustomer);

        returnDTO.setCustomerUrl(API_V1_CUSTOMER + savedCustomer.getId());

        return returnDTO;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
