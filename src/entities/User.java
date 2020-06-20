package entities;

public class User {

    // TODO Add Constructor, Getters & Setters

    private Long id;
    private String email;
    private String password;

    public User(Long id, String email, String password) {
        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return String.format("%d,%s,%s,%s", getId(), getEmail(), getPassword());
    }


}