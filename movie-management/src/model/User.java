package model;

import java.sql.Date;

public class User {
    public int id;
    public String name;
    public String password;
    public String email;
    public int age;
    public String phoneNumber;
    public String role;
    public boolean isActive;
    public Date registrationDate;
    
    public User() {
    }
    
    public void displayUserInfo() {
        System.out.println("User: " + name + " (" + role + ")");
    }
}

