package entities;

public class User {

    // private static final String[] roles = {"transportist", "sanitation", "admin"}; // me gustaría más un enum.. pero dijo que eso no lo vimos y mejor no lo use xD

    private Long id;
    private String email;
    private String password;
    private String role;

    public User(Long id, String email, String password, String role) {
        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    public User(String email, String password, String role) {
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }


    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return String.format("%d,%s,%s,%s", getId(), getEmail(), getPassword(), getRole());
    }


}