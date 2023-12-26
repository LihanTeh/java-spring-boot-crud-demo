package com.springbootcrud.demo.service;

import com.springbootcrud.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Page<Customer> findAll(Pageable pageable);
    Customer findById(int id);
    Customer save(Customer customer);
    List<Customer> saveAll(List<Customer> customers);
    void deleteById(int id);
}
