package com.example.Projekat_web.repositories.kategorija;

import com.example.Projekat_web.HelloApplication;
import com.example.Projekat_web.entities.Kategorija;
import com.example.Projekat_web.entities.Korisnik;
import com.example.Projekat_web.entities.Login.LoginResponse;
import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.repositories.MySqlAbstractRepository;
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

public class MySqlKategorijaRepository extends MySqlAbstractRepository implements KategorijaInterface {


    @Override
    public List<Kategorija> getKategorija() {
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorija");
            while (resultSet.next()) {

                Kategorija kategorija = new Kategorija(resultSet.getInt("id"), resultSet.getString("ime"),
                        resultSet.getString("opis"));

                kategorije.add(kategorija);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorije;

    }

    @Override
    public Kategorija addKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO kategorija (ime , opis) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, kategorija.getIme());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                kategorija.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorija;

    }

    @Override
    public Kategorija updateKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();




            preparedStatement = connection.prepareStatement("UPDATE kategorija SET ime = ?, opis= ? WHERE id = ?");
            preparedStatement.setString(1, kategorija.getIme());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.setInt(3, kategorija.getId());
            preparedStatement.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);

            this.closeConnection(connection);
        }

        return kategorija;
    }

    @Override
    public void deleteKategorija(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from vest where fk_kategorija = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            int br=0;

            while(resultSet.next()){
                br++;
            }

            if(br == 0){
                preparedStatement = connection.prepareStatement("DELETE FROM kategorija WHERE id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Vest> vestiKategorija(Integer id) {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {



            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from vest where fk_kategorija = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){

                Vest vest = new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("text"),
                        resultSet.getString("vreme_kreiranja"), resultSet.getInt("broj_poseta"),
                        resultSet.getInt("fk_korisnik"), resultSet.getInt("fk_kategorija"));

                vesti.add(vest);

            }





        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }
}
