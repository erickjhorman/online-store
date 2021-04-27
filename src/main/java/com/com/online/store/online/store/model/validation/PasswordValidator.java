package com.com.online.store.online.store.model.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Pattern pattern;
    private Matcher matcher;

    /*
    Be between 8 and 40 characters long
    Contain at least one digit.
    Contain at least one lower case character.
    Contain at least one upper case character.
    Contain at least on special character from [ @ # $ % ! . ].
     */
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    //Java is used to create a pattern from the regular expression passed as parameter to method.
    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }


    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void initialize(Password constraintAnnotation) {

    }
}
