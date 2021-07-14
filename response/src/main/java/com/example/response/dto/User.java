package com.example.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

//보통 ScakeCase많이 사용. 그 외의 경우는 JsonProperty을 사용하는 것이 좋음
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//Json의 속성값이 null값인 경우 포함하지 않으려면 JsonInclude사용
@JsonInclude(JsonInclude.Include.NON_NULL) //NotNull만 포함하겠다.
public class User {
    private String name;
    private Integer age;

    //@JsonProperty("phone_number")
    private String phoneNumber;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
