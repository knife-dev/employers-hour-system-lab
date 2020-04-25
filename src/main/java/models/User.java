package main.java.models;

public class User {

    // TODO Add Constructor, Getters & Setters

    private Long id;
    private String email;
    private String password;
    private String userType;

    public User(Long id, String email, String password, String userType) {
        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
        this.setUserType(userType);
    }

    public User(String email, String password, String userType) {
        this.setEmail(email);
        this.setPassword(password);
        this.setUserType(userType);
    }

    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String toString() {
        return String.format("%d,%s,%s,%s", getId(), getEmail(), getPassword(), getUserType());
    }


}