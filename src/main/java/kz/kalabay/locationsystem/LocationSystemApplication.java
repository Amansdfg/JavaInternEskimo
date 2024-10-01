package kz.kalabay.locationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LocationSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationSystemApplication.class, args);
    }
}
