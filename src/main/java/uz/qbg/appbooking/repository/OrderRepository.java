package uz.qbg.appbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.qbg.appbooking.entity.Order;

public interface OrderRepository extends JpaRepository <Order ,Integer>{


}
