package uz.qbg.appbooking.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.qbg.appbooking.entity.Order;
import uz.qbg.appbooking.payload.ApiRespons;
import uz.qbg.appbooking.payload.HotelDTO;
import uz.qbg.appbooking.payload.OrderDto;
import uz.qbg.appbooking.payload.RoomDTO;
import uz.qbg.appbooking.repository.OrderRepository;
import uz.qbg.appbooking.sevice.client.HotelClientService;
import uz.qbg.appbooking.sevice.client.RoomClientService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final HotelClientService hotelClientService;

    private final RoomClientService roomClientService;

    public ApiRespons createOrder(OrderDto orderDto) {
        Order order = new Order();
        HotelDTO hotelByID = hotelClientService.getHotelByID(orderDto.getHotelId());
        if (hotelByID != null) {
            order.setHotelId(orderDto.getHotelId());
        } else {
            throw new RuntimeException("Hotel with this ID does not exists");
        }
        order.setCustomerId(orderDto.getCustomerId());
        RoomDTO roomByID = roomClientService.getRoomByID(orderDto.getRoomId());
        if (roomByID != null) {
            order.setRoomId(orderDto.getRoomId());
        } else {
            throw new RuntimeException("Room with this ID does not exists");
        }
        order.setFrom(LocalDate.now());
        order.setTo(LocalDate.now().plusDays(5));
        orderRepository.save(order);
        return new ApiRespons("buyurtma qabul qilindi", true);
    }

    public Page<Order> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
        return orderRepository.findAll(pageable);
    }

    public Order getByOrderID(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("bunday buyurtma ro'yhatda yo'q"));

    }

    public ApiRespons updateById(OrderDto orderDto) {
        if (orderRepository.findById(orderDto.getId()).isPresent())
            return new ApiRespons("siz qidirgan buyurtma topilmadi", false);
        Order order = new Order();
        order.setHotelId(orderDto.getHotelId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setRoomId(orderDto.getRoomId());
        order.setFrom(orderDto.getFrom());
        order.setTo(orderDto.getTo());
        orderRepository.save(order);
        return new ApiRespons("buyurtma o'zgartirildi", true);

    }

    public ApiRespons deleteById(Integer id) {
        if (!orderRepository.findById(id).isPresent())
            return new ApiRespons("siz qidirgan buyurtma topilmadi", false);
        orderRepository.deleteById(id);
        return new ApiRespons("buyurtma o'chirildi", true);
    }
}
