package com.com.online.store.online.store.model.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Pattern pattern;
    /*
    Be between 8 and 40 characters long
    Contain at least one digit.
    Contain at least one lower case character.
    Contain at least one upper case character.
    Contain at least on special character from [ @ # $ % ! . ].
     */

    @Value("${password.pattern}")
    private String passwordPattern;

    //Java is used to create a pattern from the regular expression passed as parameter to method.
    public PasswordValidator() {
        pattern = Pattern.compile(passwordPattern);
    }


    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
