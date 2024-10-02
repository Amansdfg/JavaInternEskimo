package kz.kalabay.locationsystem.services;

import kz.kalabay.locationsystem.models.dtos.UserDto;
import kz.kalabay.locationsystem.mapper.MapperUser;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapperUser mapperUser;

    public UserDto registerUser(UserDto userDTO) {

        if (userDTO == null) {
            throw new IllegalArgumentException("userDTO is null");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return mapperUser.mapToDTO(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {

        return mapperUser.mapToDTOList(userRepository.findAll());
    }
}
