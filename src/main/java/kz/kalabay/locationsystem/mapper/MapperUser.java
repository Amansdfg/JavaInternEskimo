package kz.kalabay.locationsystem.mapper;

import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.dtos.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")

public interface MapperUser {
    UserDto mapToDTO(User user);
    List<UserDto> mapToDTOList(List<User> users);
}