package com.example.lab_by056;

public abstract class User {
    //Attribute
    private String username;
    private int age;
    private String gender;

    //Method
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void login() {
        System.out.println(" Welcome User " + username) ;
    }
}
