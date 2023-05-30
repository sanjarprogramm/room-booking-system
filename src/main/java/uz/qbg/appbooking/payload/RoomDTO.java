package uz.qbg.appbooking.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Integer id;
    private String status;
    private String roomNumber;
    private Integer floor ;
    private Integer hotelId ;
    private String price;
    private boolean empty;
}
