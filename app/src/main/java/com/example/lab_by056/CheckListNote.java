package com.example.lab_by056;

import java.util.List;

public class CheckListNote extends Note {
    //Attribute
    private List<String> checkList ;

    //Method
    public void getSummary(){
        if (checkList != null) {
            System.out.println(getTitle() + " (Checklist): " + checkList.size() + " items");
        } else {
            System.out.println(getTitle() + " (Checklist): 0 items");
        }
    }
    public List<String> getCheckList(){
        return checkList ;
    }
    public void setCheckList(List<String> newCheckList){
        this.checkList = newCheckList ;
    }
}
