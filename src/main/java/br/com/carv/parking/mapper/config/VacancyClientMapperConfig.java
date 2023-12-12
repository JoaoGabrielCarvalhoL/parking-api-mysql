package br.com.carv.parking.mapper.config;

import br.com.carv.parking.mapper.VacancyClientMapper;
import br.com.carv.parking.mapper.impl.VacancyClientMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VacancyClientMapperConfig {

    @Bean
    VacancyClientMapper vacancyClientMapper() {
        return new VacancyClientMapperImpl();
    }
}
