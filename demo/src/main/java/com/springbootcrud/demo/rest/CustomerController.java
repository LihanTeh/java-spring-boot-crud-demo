package com.springbootcrud.demo.rest;

import com.springbootcrud.demo.entity.Account;
import com.springbootcrud.demo.entity.Customer;
import com.springbootcrud.demo.service.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;
    private List<Customer> dummyCustomers = new ArrayList<>();

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostConstruct
    public void loadDummyData(){
        dummyCustomers.add(new Customer(
                "012-3456789", "ali@gmail.com", "123, Awesome Town",
                new Account("Free", "Ali", new BigDecimal("556.78"))));
        dummyCustomers.add(new Customer(
                "013-666989", "becky@gmail.com", "999, Gorgeous Town",
                new Account("Premium", "Becky", new BigDecimal("99999.99"))));
        dummyCustomers.add(new Customer(
                "018-9456000", "carlie@gmail.com", "6, Beautiful City",
                new Account("Free", "Carlie", new BigDecimal("6.66"))));
        dummyCustomers.add(new Customer(
                "012-5558080", "danny@gmail.com", "55 Biggest City",
                new Account("Free", "Danny", new BigDecimal("50"))));
        dummyCustomers.add(new Customer(
                "017-333888", "emma@gmail.com", "1 First Street",
                new Account("Premium", "Emma", new BigDecimal("888.88"))));
        dummyCustomers.add(new Customer(
                "019-876543", "felicia@gmail.com", "Homeless",
                new Account("Free", "Felicia", new BigDecimal("0"))));
        dummyCustomers.add(new Customer(
                "011-034567", "gabby@gmail.com", "30, Best House Ever",
                new Account("Free", "Gabby", new BigDecimal("835.60"))));
        dummyCustomers.add(new Customer(
                "016-5559999", "hafiz@gmail.com", "50, Jalan Raja",
                new Account("Premium", "Hafiz", new BigDecimal("5000"))));
        dummyCustomers.add(new Customer(
                "017-6543210", "ivan@gmail.com", "The Igloo",
                new Account("Free", "Ivan", new BigDecimal("76.30"))));
        dummyCustomers.add(new Customer(
                "011-2223333", "jacob@gmail.com", "6, Earth Village",
                new Account("Premium", "Jacob", new BigDecimal("77.77"))));
        dummyCustomers.add(new Customer(
                "013-9998888", "kaz@gmail.com", "3, Jalan Sultan",
                new Account("Premium", "Kaz", new BigDecimal("3"))));
    }


    @GetMapping("/customers")
    public Page<Customer> getAllDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.findAll(pageable);
    }

    @GetMapping("/customers/{customerId}")
    public Customer getDetailById(@PathVariable int customerId){
        return customerService.findById(customerId);
    }


    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        customer.setCustomerId(0);
        customer.getAccount().setAccountNumber(0);
        return customerService.save(customer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){

        return customerService.save(customer);
    }

    @DeleteMapping("/customers/{customer_id}")
    public String deleteCustomer(@PathVariable int customer_id){
        customerService.deleteById(customer_id);
        return "Customer with id " + customer_id + " is deleted.";
    }


    @PostMapping("/customers/generate")
    public Iterable<Customer> generateCustomers(){
        for(Customer customer : dummyCustomers){
            customer.setCustomerId(0);
        }
        return customerService.saveAll(dummyCustomers);
    }
}
