package rs.raf.demo.entities;

public class LoginResponse {

    private String token;
    private String msg;
    private String role;
    private Integer id;

    public LoginResponse(String token, String msg, String role, Integer id) {
        this.token = token;
        this.msg = msg;
        this.role = role;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
