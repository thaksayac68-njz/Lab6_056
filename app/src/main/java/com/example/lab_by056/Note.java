package com.example.lab_by056;

public abstract class Note {
    //Attribute
    private String title;
    public String createdDate;
    private User owner;

    //Method
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public User getOwner() {
        return owner;
    }
    public void getSummary() {
        System.out.println(title + "(" + createdDate + ")");
    }
    public String getTitle(){
        return title ;
    }
    public void setTitle (String newTitle){
        this.title = newTitle ;
    }
    public String getCreatedDate() {
        return createdDate ;
    }
    public void setCreatedDate(String newCreatedDate) {
        this.createdDate = newCreatedDate ;
    }
}
