package kz.kalabay.locationsystem.controller;

import kz.kalabay.locationsystem.dtos.UserDto;
import kz.kalabay.locationsystem.mapper.MapperUser;
import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LocationController {
    @PostMapping
    public ResponseEntity<Location> registerUser(@RequestBody Location location) {
        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }
}
