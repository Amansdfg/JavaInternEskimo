package kz.kalabay.locationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class LocationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationSystemApplication.class, args);
    }
//    public static void main(String[] args) {
//        try (Connection conn = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5435/partnerTest", "postgres", "aman06")) {
//            System.out.println("Connection successful!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
