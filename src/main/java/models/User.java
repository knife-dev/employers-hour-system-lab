package main.java.models;

public class User {

    // TODO Add Constructor, Getters & Setters

    private Integer id;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
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


    public String toString() {
        return String.format("%d,%s,%s", getId(), getEmail(), getPassword());
    }


}