package uz.qbg.appbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uz.qbg.appbooking.configs.FeignClientConfig;
import uz.qbg.appbooking.constants.Constants;

@SpringBootApplication(scanBasePackages = Constants.BASE_PACKAGE)
@EnableWebMvc
@EnableFeignClients
@Import(FeignClientConfig.class)
public class AppBookingApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppBookingApplication.class, args);
	}
}
