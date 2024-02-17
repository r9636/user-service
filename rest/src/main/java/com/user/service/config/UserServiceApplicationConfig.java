package com.user.service.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class UserServiceApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.modules(new JavaTimeModule());
    }

    @Bean
    public ObjectMapper objectMapper() {
        final JavaTimeModule module = new JavaTimeModule();
        final LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE);
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        module.addSerializer(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
            @Override
            public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")));
            }
        });        module.addDeserializer(ZonedDateTime.class, new JsonDeserializer<ZonedDateTime>() {
            @Override
            public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ZonedDateTime.parse(jsonParser.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            }
        });
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("Student Management Project"));
    }
}
