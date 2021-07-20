package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {YearMonthValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)

public @interface YearMonth {
    String message() default "yyyyMM의 형식과 맞지 않습니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    String pattern() default "yyyyMMdd"; //logic처리 시 value에 01을 붙여주기 때문에 형식을 yyyyMMdd로 넣어야함
}
