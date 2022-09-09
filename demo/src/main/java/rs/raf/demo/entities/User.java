package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    private Integer user_id;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String name;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String lastname;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String role;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String status;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String password;

    public User() {
    }

    public User(Integer user_id, String email, String name, String lastname, String role, String status, String password) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
