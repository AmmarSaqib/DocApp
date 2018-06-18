package com.example.ammarsaqib.docapp;

public class doctor_data {

    String name, specialization, hospital, contact_no, img;
    int fee;

    public doctor_data()
    {}

    //    public doctor_data(String name, String specialization, int fee, String hospital, String contact_no) {
    public doctor_data(String name, String specialization, int fee, String hospital, String contact_no, String img) {
        this.name = name;
        this.specialization = specialization;
        this.hospital = hospital;
        this.contact_no = contact_no;
        this.fee = fee;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
