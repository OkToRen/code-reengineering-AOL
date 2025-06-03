package model;

import java.sql.Date;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int age;
    private String phoneNumber;
    private UserRole role;
    private boolean isActive;
    private Date registrationDate;

	public User(int id, String name, String password, String email, int age, String phoneNumber, UserRole role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.isActive = true;
		this.registrationDate = new Date(System.currentTimeMillis());
	}
    
    public void displayUserInfo() {
        System.out.println("User: " + name + " (" + role + ")");
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}

