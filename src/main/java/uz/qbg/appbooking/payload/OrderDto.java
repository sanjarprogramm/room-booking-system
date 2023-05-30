package uz.qbg.appbooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer id;

    private Integer hotelId;

    private Integer roomId;

    private Integer customerId;

    private LocalDate from;

    private LocalDate to;


}
