package kz.kalabay.locationsystem.services;

import kz.kalabay.locationsystem.models.dtos.UserDto;
import kz.kalabay.locationsystem.mapper.MapperUser;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private MapperUser mapperUser;
    public UserDto registerUser(UserDto userDTO) {
        User user=new User();
        user.setEmail(userDTO.getEmail());
        return mapperUser.mapToDTO(userRepository.save(user));
    }
    public List<UserDto> getAllUsers() {
        return mapperUser.mapToDTOList(userRepository.findAll());
    }
}
