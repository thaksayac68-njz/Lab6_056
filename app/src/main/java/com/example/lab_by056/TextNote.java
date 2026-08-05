package com.example.lab_by056;

public class TextNote extends Note{
    //Attribute
    private String content ;

    //Method
    public void getSummary() {
        System.out.println(getTitle() + ":" + content + "(" + getCreatedDate() + ")") ;
    }
    public String getContent(){
        return content ;
    }
    public void setContent(String newContent){
        this.content = newContent ;
    }

}
