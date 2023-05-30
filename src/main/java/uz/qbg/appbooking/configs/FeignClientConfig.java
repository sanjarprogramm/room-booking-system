package uz.qbg.appbooking.configs;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
