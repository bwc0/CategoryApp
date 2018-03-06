package bears.beats.battlestargalatica.api.v1.mapper;

import bears.beats.battlestargalatica.api.v1.dto.CustomerDTO;
import bears.beats.battlestargalatica.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
