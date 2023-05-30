package uz.qbg.appbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
import uz.qbg.appbooking.entity.Order;
import uz.qbg.appbooking.payload.ApiRespons;
import uz.qbg.appbooking.payload.OrderDto;
import uz.qbg.appbooking.sevice.OrderService;

@RestController
@RequestMapping (value = "/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/create")
    public HttpEntity<?> create (@RequestBody OrderDto orderDto){
        ApiRespons apiRespons = orderService.createOrder(orderDto);
        return ResponseEntity .status(apiRespons.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiRespons);
    }
    @GetMapping
    public HttpEntity<?> getAll (@RequestParam int page ,@RequestParam int size){
        Page<Order> all = orderService.getAll(page, size);
        return ResponseEntity.ok(all);

    }

    @GetMapping(value = "/{id}")
    public HttpEntity <?> readById(@PathVariable Integer id){
        Order byOrderID = orderService.getByOrderID(id);
        return ResponseEntity.ok(byOrderID);

    }

    @PutMapping(value = "/update/{id}")
    public HttpEntity<?>edit (@RequestBody OrderDto orderDto){

        ApiRespons apiRespons = orderService.updateById(orderDto);
        return ResponseEntity.status(apiRespons.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiRespons);


    }

    @DeleteMapping(value = "/delete/{id}")
    public HttpEntity<?> delete(@RequestParam Integer id){
        ApiRespons apiRespons = orderService.deleteById(id);
        return ResponseEntity.status(apiRespons.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.CONFLICT).body(apiRespons);
    }
}
