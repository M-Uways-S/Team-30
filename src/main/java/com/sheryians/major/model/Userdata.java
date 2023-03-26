package com.sheryians.major.model;

import javax.persistence.*;;

@Entity
@Table(name = "USERDATA")
public class Userdata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numb")
    private int numb;

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "secondname")
    private String secondname;

    @Column(name = "email")
    private String email;

    @Column(name = "Phonenumber")
    private String Phonenumber;

    @Column(name = "category")
    private String category;

    @Column(name = "texta", length = 5000)
    private String texta;

    public String getTexta() {
        return texta;
    }

    public void setTexta(String texta) {
        this.texta = texta;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Userdata{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", email='" + email + '\'' +
                ", Phonenumber='" + Phonenumber + '\'' +
                ", category='" + category + '\'' +
                ", texta='" + texta + '\'' +
                '}';
    }
}