package com.springbootcrud.demo.service;

import com.springbootcrud.demo.dao.CustomerRepository;
import com.springbootcrud.demo.entity.Customer;
import com.springbootcrud.demo.rest.CustomerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if(result.isPresent()){
            customer = result.get();
        }else{
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }
        return customer;
    }


    @Override
    public Customer save(Customer customer) {
        if (customer.getCustomerId() != 0) {
            Customer existingCustomer = findById(customer.getCustomerId());
            customer.getAccount().setAccountNumber(existingCustomer.getAccount().getAccountNumber());
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    @Override
    public void deleteById(int id) {
        findById(id);
        customerRepository.deleteById(id);
    }

}
