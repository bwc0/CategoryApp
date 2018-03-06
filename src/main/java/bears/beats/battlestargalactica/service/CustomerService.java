package bears.beats.battlestargalactica.service;

import bears.beats.battlestargalactica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalactica.api.v1.dto.CustomerListDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomersById(Long id);
}
