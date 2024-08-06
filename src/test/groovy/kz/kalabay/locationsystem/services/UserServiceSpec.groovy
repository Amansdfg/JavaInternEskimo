package kz.kalabay.locationsystem.services

import kz.kalabay.locationsystem.mapper.MapperUser
import kz.kalabay.locationsystem.models.User
import kz.kalabay.locationsystem.models.dtos.UserDto
import kz.kalabay.locationsystem.repositories.UserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification{
    UserRepository userRepository = Mock()
    MapperUser mapperUser = Mock()
    UserService userService = new UserService(userRepository: userRepository, mapperUser: mapperUser)
    def "should register a user and return user DTO"(){
        given:
        UserDto userDto =new UserDto(name: "Aman Kalabay", email: "aman@gmail.com")
        User user =new User(name:"Aman Kalabay", email: "aman@gmail.com")
        mapperUser.mapToDTO(_) >>userDto
        userRepository.save(_) >>user
        when:
        UserDto result =userService.registerUser(userDto)
        then:
        result.name =="Aman Kalabay"
        result.email =="aman@gmail.com"
    }
    def "should return all users as DTOs"(){
        given:
        UserDto userDto =new UserDto(name: "Aman Kalabay", email: "aman@gmail.com")
        User user =new User(name:"Aman Kalabay", email: "aman@gmail.com")
        userRepository.findAll()>>[user]
        mapperUser.mapToDTOList([user])>>[userDto]
        when:
        List<UserDto> result=userService.getAllUsers()
        then:
        result.size()==1
        result[0].name=="Aman Kalabay"
        result[0].email=="aman@gmail.com"
    }
}
