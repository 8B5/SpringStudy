package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User {

    @NotBlank //null, 공백 못들어옴
    private String name;

    @Max(value = 90)
    private int age;
    /*
    @Email //email의 양식과 다른 값이 들어오면 에러 발생
    private String email;
    
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
            message = "핸드폰번호의 양식과 맞지 않습니다.01x-xxxx-xxxx") //regexp : 정규식

    private String phoneNumber;


    //@Size(min=6, max=6)
   @YearMonth //default (pattern = "yyyyMM")
    private String reqYearMonth; //yyyyMM
*/
    @Valid
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
/*
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }
*/
    //YearMonthValidator로 붙여줌
  //  @AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")
  //  public boolean isreqYearMonthValidation(){ //@AssertTrue사용 시 is 키워드로 시작해야함

  //  }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
/*
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", cars=" + cars +
                '}';
    }

 */

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}
