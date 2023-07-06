package shubh.springframework.spring6reactive.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shubh.springframework.spring6reactive.model.CustomerDTO;

public interface CustomerService {
    Flux<CustomerDTO> listCustomers();
    Mono<CustomerDTO> getCustomerById(Integer customerId);
    Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO);
    Mono<CustomerDTO> updateCustomer(Integer customerId, CustomerDTO customerDTO);
    Mono<CustomerDTO> patchCustomer(Integer customerId, CustomerDTO customerDTO);
    Mono<Void> deleteCustomer(Integer customerId);


}
