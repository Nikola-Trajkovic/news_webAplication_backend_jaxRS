package com.example.Projekat_web.repositories.korisnik;

import com.example.Projekat_web.HelloApplication;
import com.example.Projekat_web.entities.Korisnik;
import com.example.Projekat_web.entities.Login.Login;
import com.example.Projekat_web.entities.Login.LoginResponse;
import com.example.Projekat_web.repositories.MySqlAbstractRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySqlKorisnikRepository extends MySqlAbstractRepository implements KorisnikInterface {
    @Override
    public List<Korisnik> getKorisnik() {
        List<Korisnik> korisniks = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnik");
            while (resultSet.next()) {

                Korisnik korisnik =  new Korisnik(resultSet.getInt("ID"), resultSet.getString("Ime"), resultSet.getString("Prezime"),
                        resultSet.getString("Email"), resultSet.getString("Tip"),
                        resultSet.getString("Status"), resultSet.getString("Lozinka"));
                korisniks.add(korisnik);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisniks;
    }

    @Override
    public Korisnik addKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO korisnik (ime , prezime, email, tip, status, lozinka) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getPrezime());
            preparedStatement.setString(3, korisnik.getEmail());
            preparedStatement.setString(4, "user");
            preparedStatement.setString(5, "aktivan");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    korisnik.getLozinka().getBytes(StandardCharsets.UTF_8));
            String sha256hex = new String(Hex.encode(hash));
            preparedStatement.setString(6, sha256hex);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                korisnik.setId(resultSet.getInt(1));
            }

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return korisnik;
    }

    @Override
    public LoginResponse login(Login login) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik WHERE email = ? AND lozinka = ?");
            preparedStatement.setString(1, login.getEmail());
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    login.getLozinka().getBytes(StandardCharsets.UTF_8));
            String sha256hex = new String(Hex.encode(hash));
            System.out.println(sha256hex);
            preparedStatement.setString(2, sha256hex);
            resultSet = preparedStatement.executeQuery();

            int br=0;
            int id = 0;
            String role = "";
            String email = "";
            String password = "";
            String status = "";
            while (resultSet.next()){
                id = resultSet.getInt("id");
                role = resultSet.getString("tip");
                email = resultSet.getString("email");
                password = resultSet.getString("lozinka");
                status = resultSet.getString("status");
                br++;
            }

            if(br>0){

                if(status.equals("deaktivan")){
                    return new LoginResponse(null,"Nemate pristup sajtu dekativirani ste", null, null);
                }

                HelloApplication.email = email;
                HelloApplication.lozinka = password;
                Random random = new SecureRandom();
                String token = new BigInteger(130, random).toString(32);
                return new LoginResponse(token, null, role, id);
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

    @Override
    public void deleteKorisnik(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("DELETE FROM korisnik WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();




        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public Korisnik updateKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE korisnik SET ime = ?,  prezime = ?, email = ? , tip = ?, status = ? where id=?");
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getPrezime());
            preparedStatement.setString(3, korisnik.getEmail());
            preparedStatement.setString(4, korisnik.getTip());
            preparedStatement.setString(5, korisnik.getStatus());
            preparedStatement.setInt(6, korisnik.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnik;
    }

    @Override
    public void logOut() {
        HelloApplication.email = "";
        HelloApplication.lozinka = "";
    }
}
