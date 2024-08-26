package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI.mapper.CustomerMapper;
import com.example.BookstoreAPI.model.Customer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customerList = new ArrayList<>();

    // GET all customers or filter by name and email
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        List<Customer> filteredCustomers = customerList.stream()
                .filter(customer -> (name == null || customer.getName().equalsIgnoreCase(name)) &&
                        (email == null || customer.getEmail().equalsIgnoreCase(email)))
                .toList();

        List<CustomerDTO> customerDTOs = filteredCustomers.stream()
                .map(CustomerMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(customerDTOs);
    }

    // GET customer by ID with custom headers and status
    @GetMapping(value="/{id}",produces = { "application/json", "application/xml" })
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        CustomerDTO customerDTO = CustomerMapper.INSTANCE.toDTO(customer);
        customerDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        return ResponseEntity.ok().headers(headers).body(customerDTO);
    }

    // Create a new customer with custom headers and status
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");

        customerList.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(customerDTO);
    }

    // PUT - Update customer by ID
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");

        return ResponseEntity.ok().headers(headers).body(customerDTO);
    }

    // DELETE customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customerList.remove(customer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String address) {

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);

        customerList.add(customer);
        return ResponseEntity.ok(customer);
    }
}