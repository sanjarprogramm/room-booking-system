package uz.qbg.appbooking.sevice;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.qbg.appbooking.entity.Customer;
import uz.qbg.appbooking.payload.ApiRespons;
import uz.qbg.appbooking.repository.CustomerRepository;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public ApiRespons addCustomer(Customer customer) {
        boolean byPhoneNumber = customerRepository.existsByPhoneNumber(customer.getPhoneNumber());
        if (byPhoneNumber) {
            log.error("Jadvalda Bu telefon raqamga ega mehmon avvaldan bor, phoneNumber {}", customer.getPhoneNumber());
            return new ApiRespons("Jadvalda Bu telefon raqamga ega mehmon avvaldan bor", false);
        }
        Customer customer1 = new Customer();
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setAddress(customer.getAddress());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(customer1);
        return new ApiRespons("mijoz ro'yhatga saqlandi", true);

    }

    public Page<Customer> allCustomer(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
        return customerRepository.findAll(pageable);

    }

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("siz qidirgan mehmon jadvalda yo'q"));
    }

    public ApiRespons updateCustomer( Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        if (!optionalCustomer.isPresent())
            return new  ApiRespons ("siz qidirgan mehmon jadvalda yo'q",false);
        Customer customer1 = optionalCustomer.get();
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setAddress(customer.getAddress());
        customerRepository.save(customer1);
        return new ApiRespons("mehmon maluumotlariga o'zgartirish kiritildi",true);

    }

    public ApiRespons deletCustomer (Integer id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent())
            return new ApiRespons("jadvalda bunday id ga ega mijoz topilmadi",false);
        customerRepository.deleteById(id);
        return new ApiRespons("Jadvaldan mijoz o'chirildi",true);
    }

}
