package rs.raf.demo.services;

import rs.raf.demo.entities.LoginResponse;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.user.UserRepository;
import rs.raf.demo.requests.LoginRequest;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> allUsers(){
        return this.userRepository.allUsers();
    }

    public User addUser(User user){
        return this.userRepository.addUser(user);
    }

    public void delete(Integer id){
        this.userRepository.delete(id);
    }

    public User findUser(Integer id){
        return this.userRepository.findUser(id);
    }

    public void updateUser(User user){
        this.userRepository.updateUser(user);
    }

    public LoginResponse login(LoginRequest loginRequest){
        return userRepository.login(loginRequest);
    }


}
