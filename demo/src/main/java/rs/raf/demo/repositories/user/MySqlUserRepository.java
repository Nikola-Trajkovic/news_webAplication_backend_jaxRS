package rs.raf.demo.repositories.user;

import org.bouncycastle.util.encoders.Hex;
import rs.raf.demo.HelloApplication;
import rs.raf.demo.entities.LoginResponse;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.MySqlAbstractRepository;
import rs.raf.demo.requests.LoginRequest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository {

    @Override
    public User addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"user_id"};

            preparedStatement = connection.prepareStatement("INSERT INTO user (email , name, lastname, role, status, password) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, "active");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    user.getPassword().getBytes(StandardCharsets.UTF_8));
            String sha256hex = new String(Hex.encode(hash));
            preparedStatement.setString(6, sha256hex);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setUser_id(resultSet.getInt(1));
            }

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("user_id"), resultSet.getString("email"),
                        resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getString("role"),
                        resultSet.getString("status"), resultSet.getString("password")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public User findUser(Integer id) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM user where user_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String password = resultSet.getString("password");
                user = new User(user_id, email, name, lastname, role, status, password);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM user where user_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE user SET user.email = ?, " +
                    "user.name = ?, user.lastname = ?, user.role = ?, user.status = ?, user.password = ? where user_id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getUser_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public LoginResponse login(LoginRequest login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            System.out.println(login.getEmail() + " " + login.getPassword());

            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            preparedStatement.setString(1, login.getEmail());
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    login.getPassword().getBytes(StandardCharsets.UTF_8));
            String sha256hex = new String(Hex.encode(hash));
            System.out.println(sha256hex);
            preparedStatement.setString(2, sha256hex);
            resultSet = preparedStatement.executeQuery();

            boolean check = false;
            int user_id = 0;
            String role = "";
            String status = "";
            while (resultSet.next()){
                user_id = resultSet.getInt("user_id");
                role = resultSet.getString("role");
                status = resultSet.getString("status");
                check = true;
            }

            if(check){

                if(status.equals("inactive")){
                    return new LoginResponse(null,"Nemate pristup sajtu dekativirani ste", null, null);
                }

                Random random = new SecureRandom();
                String token = new BigInteger(130, random).toString(32);
                return new LoginResponse(token, null, role, user_id);
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

        }


        return new LoginResponse(null,"Niste lepo uneli email ili lozinku", null, null);
    }
}
