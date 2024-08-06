package kz.kalabay.locationsystem.models

import kz.kalabay.locationsystem.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class UserRepositorySpec extends Specification {

    @Autowired
    UserRepository userRepository

    def "should save and retrieve user"() {
        given:
        def user = User.builder()
                .email("test@example.com")
                .name("Test User")
                .build()

        when:
        userRepository.save(user)
        def foundUser = userRepository.findById(user.id).orElse(null)

        then:
        foundUser != null
        foundUser.email == "test@example.com"
        foundUser.name == "Test User"
    }
}
