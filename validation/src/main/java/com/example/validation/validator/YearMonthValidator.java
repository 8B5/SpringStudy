package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;
import org.hibernate.validator.cfg.context.Constrainable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;


public class YearMonthValidator implements ConstraintValidator<YearMonth,String> {

    private String pattern;
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern(); //초기화 했을 때 값
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // yyyyMM
        //day의 값이 있어야 검색이 가능해서 임의로 value()+"01" 해줌
        try {
            LocalDate localDate =
                    LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;
        }

        return true;

        //return false;
    }
}
