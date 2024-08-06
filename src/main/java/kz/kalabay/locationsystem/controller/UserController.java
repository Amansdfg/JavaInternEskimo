package kz.kalabay.locationsystem.controller;

import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.models.dtos.UserDto;
import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.services.LocationService;
import kz.kalabay.locationsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDTO));
    }
    @GetMapping("/{id}/locations")
    public ResponseEntity<List<Location>> getLocationByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(locationService.getLocationsByOwnerId(userId));
    }
    @GetMapping("/all")
    public List<UserDto>getAllUsers() {
        return userService.getAllUsers();
    }
}
