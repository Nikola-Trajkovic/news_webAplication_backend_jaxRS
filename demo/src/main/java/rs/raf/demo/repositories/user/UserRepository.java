package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.LoginResponse;
import rs.raf.demo.entities.User;
import rs.raf.demo.requests.LoginRequest;

import java.util.List;

public interface UserRepository {

    User addUser(User user);

    List<User> allUsers();

    User findUser(Integer id);

    void delete(Integer id);

    void updateUser(User user);

    LoginResponse login(LoginRequest loginRequest);
}
