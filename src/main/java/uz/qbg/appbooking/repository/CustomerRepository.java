package uz.qbg.appbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.qbg.appbooking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer ,Integer> {

boolean existsByPhoneNumber(String phoneNumber);
boolean existsByPhoneNumberAndFirstName(String phoneNumber, String fierstName);
}
