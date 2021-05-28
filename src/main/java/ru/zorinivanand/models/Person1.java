package ru.zorinivanand.models;

import javax.validation.constraints.*;

public class Person1 {
    private int id;
    @NotEmpty(message = "should not be empty")
    @Size(min = 2,max = 30,message = "between 2 to 30 char")
    private String name;
    @Min(value = 0,message = "not 0")
    @Max(value = 99,message ="very big")
    private int age;
    @NotEmpty(message = "should not be empty")
//    @Email(message = "not valid")
    private String email;




    public Person1(int id, String name, int age, String email) {
        this.id = id;
        this.name=name;
        this.age=age;
        this.email=email;
    }

    public Person1() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }


}

