package com.example.lab_by056;

public class Admin extends User {
    //Attribute
    private String department ;

    //Method
    public String getDepartment() {
        return department ;
    }
    public void setDepartment(String department){
        this.department = department ;
    }
    public void editStudent() {
        System.out.println(getUsername() + " Edit student information. " + department + " department");
    }
}
