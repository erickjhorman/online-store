package com.com.online.store.online.store.dto;

import com.com.online.store.online.store.model.City;
import com.com.online.store.online.store.model.Country;
import com.com.online.store.online.store.model.User;
import com.com.online.store.online.store.model.validation.Password;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
