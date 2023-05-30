package uz.qbg.appbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.qbg.appbooking.entity.Customer;
import uz.qbg.appbooking.payload.ApiRespons;
import uz.qbg.appbooking.sevice.CustomerService;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/create")
    public HttpEntity <?> create (@RequestBody Customer customer){
        ApiRespons apiRespons = customerService.addCustomer(customer);
        return  ResponseEntity.status(apiRespons.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiRespons);

    }

    @GetMapping
    public HttpEntity<?>getAll(@RequestParam int page,@RequestParam int size){
        Page<Customer> customerPage = customerService.allCustomer(page, size);
        return ResponseEntity .ok(customerPage);

    }

    @GetMapping(value = "/{id}")
    public HttpEntity <?> getById (@RequestParam Integer id){
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping (value = "/update/{id}")
    public HttpEntity<?>update(@RequestBody Customer customer){
        ApiRespons apiRespons = customerService.updateCustomer(customer);
        return ResponseEntity.status(apiRespons.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiRespons);
    }

    @DeleteMapping (value = "/delete/{id}")
    public HttpEntity<?>delete(@RequestParam Integer id){
        ApiRespons apiRespons = customerService.deletCustomer(id);
        return ResponseEntity.status(apiRespons.isSuccess()?200:409).body(apiRespons);

    }

}
