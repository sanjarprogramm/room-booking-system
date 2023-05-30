package uz.qbg.appbooking.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiRespons {


    private String message;

    private boolean success;


    private Object object;




    public ApiRespons (String message, boolean success){
        this.message=message;
        this.success=success;
    }
}
