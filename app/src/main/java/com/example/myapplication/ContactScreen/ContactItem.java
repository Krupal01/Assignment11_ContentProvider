package com.example.myapplication.ContactScreen;
public class ContactItem {
    String Name,Phone,ImgSrc;

    public ContactItem(String name, String phone, String imgSrc) {
        Name = name;
        Phone = phone;
        ImgSrc = imgSrc;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        ImgSrc = imgSrc;
    }
}
