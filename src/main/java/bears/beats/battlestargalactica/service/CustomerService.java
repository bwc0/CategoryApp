package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalactica.api.v1.dto.CustomerListDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomersById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}
