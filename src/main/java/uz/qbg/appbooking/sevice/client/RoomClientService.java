package uz.qbg.appbooking.sevice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.qbg.appbooking.payload.HotelDTO;
import uz.qbg.appbooking.payload.RoomDTO;

@FeignClient(name = "room-service", url = "${room.service.url}")
public interface RoomClientService {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    RoomDTO getRoomByID(@PathVariable Integer id);
}
