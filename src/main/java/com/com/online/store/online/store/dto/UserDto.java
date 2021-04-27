package com.com.online.store.online.store.dto;

import com.com.online.store.online.store.model.City;
import com.com.online.store.online.store.model.Country;
import com.com.online.store.online.store.model.User;
import com.com.online.store.online.store.model.validation.Password;
import com.com.online.store.online.store.util.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Data
@RequiredArgsConstructor
public class UserDto {

    private Long idUser;

    @NotEmpty(message = "{user.name.empty}")
    @Length(min = 2, max = 25, message = "{user.name.length}")
    private String name;

    @NotEmpty(message = "{user.email.empty}")
    @Email(message = "{user.email}")
    private String email;

    @NotEmpty(message = "Password may not be empty")
    @Password
    private String password;

    @NotNull(message = "{user.lastname.required}")
    @Length(min = 1, max = 25, message = "{user.lastname.length}")
    private String lastName;

    @Past(message = "{user.birthday.past}")
    @NotNull(message = "{user.birthday.required}")
    private LocalDate birthday;

    @Past(message = "{user.created_at.past}")
    private LocalDateTime createdAt;

    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull
    private Country country;

    @NotNull
    private City city;

    public User toEntity() {
        User user = new User();
        user.setName(getName());
        user.setLastName(getLastName());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setBirthday(getBirthday());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdateAt(null);
        user.setCountry(getCountry());
        user.setCity(getCity());
        return user;
    }


}
