import kz.kalabay.locationsystem.mapper.MapperUser
import kz.kalabay.locationsystem.models.User
import kz.kalabay.locationsystem.models.dtos.UserDto
import kz.kalabay.locationsystem.repositories.UserRepository
import kz.kalabay.locationsystem.services.UserService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class UserServiceSpec extends Specification {

    @SpringBean
    UserRepository userRepository = Mock()

    @SpringBean
    MapperUser mapperUser = Mock()

    @Autowired
    UserService userService

    def "should register a user and return user DTO"() {
        given:
        UserDto userDto = new UserDto(name: "John Doe", email: "john.doe@example.com")
        User user = new User(id: 1L, name: "John Doe", email: "john.doe@example.com")

        mapperUser.mapToDTO(_) >> userDto
        userRepository.save(_) >> user

        when:
        UserDto result = userService.registerUser(userDto)

        then:
        result.name == "John Doe"
        result.email == "john.doe@example.com"
    }

    def "should return all users as DTOs"() {
        given:
        User user = new User(id: 1L, name: "John Doe", email: "john.doe@example.com")
        UserDto userDto = new UserDto(name: "John Doe", email: "john.doe@example.com")

        userRepository.findAll() >> [user]
        mapperUser.mapToDTOList(_) >> [userDto]

        when:
        List<UserDto> result = userService.getAllUsers()

        then:
        result.size() == 1
        result[0].name == "John Doe"
        result[0].email == "john.doe@example.com"
    }
}
