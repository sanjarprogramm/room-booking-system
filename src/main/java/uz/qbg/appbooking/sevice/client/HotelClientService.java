package uz.qbg.appbooking.sevice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.qbg.appbooking.payload.HotelDTO;

@FeignClient(name = "hotel-service", url = "${hotel.service.url}")
public interface HotelClientService {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    HotelDTO getHotelByID(@PathVariable Integer id);
}
