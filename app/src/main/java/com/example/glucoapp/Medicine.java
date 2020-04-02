package com.example.glucoapp;

public class Medicine {
    private String med_name,med_time;
    Medicine(String name,String time){

        this.med_name=name;
        this.med_time=time;
    }

    public String getMed_name() {
        return med_name;
    }

    public String getMed_time() {
        return med_time;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public void setMed_time(String med_time) {
        this.med_time = med_time;
    }
}
