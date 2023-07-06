package shubh.springframework.spring6reactive.mappers;

import org.mapstruct.Mapper;
import shubh.springframework.spring6reactive.domain.Customer;
import shubh.springframework.spring6reactive.model.CustomerDTO;

@Mapper
public interface CustomerMapper {
    Customer CustomerDTOtoCustomer(CustomerDTO customerDTO);

    CustomerDTO CustomertoCustomerDTO(Customer customer);
}
