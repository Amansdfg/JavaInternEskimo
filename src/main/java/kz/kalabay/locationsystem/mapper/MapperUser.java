package kz.kalabay.locationsystem.mapper;

import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.models.dtos.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MapperUser {
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    UserDto mapToDTO(User user);
    List<UserDto> mapToDTOList(List<User> users);
}