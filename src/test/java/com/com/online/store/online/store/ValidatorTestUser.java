package com.com.online.store.online.store;


import com.com.online.store.online.store.model.Country;
import com.com.online.store.online.store.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

 class ValidatorTestUser {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    void setup() {
        userObj();
    }

    @Test
    @DisplayName("Should validate not null fields")
     testNotNull() {
        Set<ConstraintViolation<User>> violations = validator.validate(userObj());
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Should validate Name Length between 2 and 25")
    void testNameLength() {
        int nameLength = userObj().getName().length();
        boolean result = false;
        if (nameLength >= 2 && nameLength <= 25) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    @DisplayName("Should validate empty & Null values")
     void testNotEmpty() {
        Set<ConstraintViolation<User>> violations = validator.validate(new User());
        assertEquals(5, violations.size());
        violations.forEach(v -> System.out.println(
                v.getPropertyPath() + " : " + v.getMessageTemplate() + " = " + v.getMessage()));
    }

    @Test
    @DisplayName("Should Validate The Email")
     void testEmail() {
        User user = userObj();
        user.setEmail("mail.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals("mail.com",user.getEmail());
    }

    private User userObj() {
        User user = User.builder().userId(3232323L)
                .name("Erick jhorman Romero Jojo")
                .lastName("Romero")
                .password("elmejor")
                .birthday(LocalDate.of(1993, 01, 29))
                .createdAt(LocalDateTime.now())
                .updateAt(null)
                .build();
        return user;
    }

    @Test
     void testDefaultLanguage() {
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundle rb = ResourceBundle.getBundle("ValidationMessages");

        Set<ConstraintViolation<User>> violations = validator.validate(new User());

        assertTrue(violations.stream().anyMatch(v->v.getMessage().equals(rb.getString("user.name.empty"))));
    }

    @Test
     void testEsLanguage() {
        Locale.setDefault(new Locale("es"));
        ResourceBundle rb = ResourceBundle.getBundle("ValidationMessages_es");

        Set<ConstraintViolation<User>> violations = validator.validate(new User());
        assertTrue(violations.stream().anyMatch(v->v.getMessage().equals(rb.getString("user.name.empty"))));
    }
}
