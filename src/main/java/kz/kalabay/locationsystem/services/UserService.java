package kz.kalabay.locationsystem.services;

import kz.kalabay.locationsystem.dtos.UserDto;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    public User registerUser(UserDto userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }
}
