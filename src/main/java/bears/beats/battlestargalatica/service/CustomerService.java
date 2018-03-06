package bears.beats.battlestargalatica.service;

import bears.beats.battlestargalatica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalatica.api.v1.dto.CustomerListDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomersById(Long id);
}
