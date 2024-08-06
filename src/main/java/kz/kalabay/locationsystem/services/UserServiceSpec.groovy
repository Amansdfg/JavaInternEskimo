package kz.kalabay.locationsystem.services

import kz.kalabay.locationsystem.models.dtos.UserDto
import kz.kalabay.locationsystem.models.User;
import spock.lang.Specification

class UserServiceSpec extends Specification {
    def userRepository = Mock(UserRepository)
    def userService = new UserService(userRepository: userRepository)

    def "registerUser should save and return a new user"() {
        given:
        def userDTO = new UserDto(name: "John Doe", email: "john.doe@example.com")

        when:
        def result = userService.registerUser(userDTO)

        then:
        1 * userRepository.save(_) >> { User u -> u }
        result.name == "John Doe"
        result.email == "john.doe@example.com"
    }
}
