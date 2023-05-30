package uz.qbg.appbooking.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.qbg.appbooking.entity.Customer;
import uz.qbg.appbooking.payload.ApiRespons;
import uz.qbg.appbooking.sevice.CustomerService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/customer")
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> create(@RequestBody Customer customer) {
        log.info("Customer yaratish uchun request, customer {}", customer);
        ApiRespons apiRespons = customerService.addCustomer(customer);
        return ResponseEntity.status(apiRespons.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiRespons);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        Page<Customer> customerPage = customerService.allCustomer(page, size);
        return ResponseEntity.ok(customerPage);

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> getById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> update(@RequestBody Customer customer) {
        ApiRespons apiRespons = customerService.updateCustomer(customer);
        return ResponseEntity.status(apiRespons.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiRespons);
    }

    @DeleteMapping(value = "/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiRespons apiRespons = customerService.deletCustomer(id);
        return ResponseEntity.status(apiRespons.isSuccess() ? 200 : 409).body(apiRespons);

    }

}
