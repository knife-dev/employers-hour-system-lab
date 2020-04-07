package main.java.models;

public class User {

    // TODO Add Constructor, Getters & Setters

    private Integer id;
    private String email;
    private String password;
    private String userType;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String toString() {
        return String.format("%d,%s,%s", getId(), getEmail(), getPassword());
    }


}