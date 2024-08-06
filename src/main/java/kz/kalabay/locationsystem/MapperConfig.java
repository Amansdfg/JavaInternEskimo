package kz.kalabay.locationsystem;

import kz.kalabay.locationsystem.mapper.MapperUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mapstruct.factory.Mappers;

@Configuration
public class MapperConfig {
    @Bean
    public MapperUser mapperUser() {
        return Mappers.getMapper(MapperUser.class);
    }
}
