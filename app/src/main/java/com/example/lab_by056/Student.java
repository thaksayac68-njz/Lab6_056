package com.example.lab_by056;

public class Student extends User {
    //Attribute
    private String studentId ;

    //Method
    public String getStudentId(){
        return studentId ;
    }
    public void setStudentId(String studentId){
        this.studentId = studentId ;
    }
    public void editNote() {
        System.out.println(getUsername() + "Student edits a note. (ID: " + studentId + ")");
    }
}
