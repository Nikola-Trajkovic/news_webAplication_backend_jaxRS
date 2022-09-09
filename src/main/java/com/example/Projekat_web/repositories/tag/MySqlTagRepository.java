package com.example.Projekat_web.repositories.tag;

import com.example.Projekat_web.HelloApplication;
import com.example.Projekat_web.entities.Komentar;
import com.example.Projekat_web.entities.Korisnik;
import com.example.Projekat_web.entities.Login.LoginResponse;
import com.example.Projekat_web.entities.Tag;
import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.repositories.MySqlAbstractRepository;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagInterface {
    @Override
    public List<Tag> getTag(Integer vestId) {
        List<Tag> tagovi = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select tag.id, tag.tag from tag join vest_tag on tag.id = vest_tag.tag_id join vest on vest.id = vest_tag.vest_id where vest.id = ?");
            preparedStatement.setInt(1, vestId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Tag tag = new Tag(resultSet.getInt("id"), resultSet.getString("tag"));
                tagovi.add(tag);

            }





        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

        }

        return tagovi;
    }

    @Override
    public Tag addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;
        boolean bol = true;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet.next()) {
                if (tag.getTag().equals(resultSet.getString("tag"))) {
                    tag.setId(resultSet.getInt("id"));
                    bol = false; // ukoliko vec imam u tabeli tag sa istim nazivom, onda ne zelim opet da dodajem taj isti tag
                }
            }

        if(bol){
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tag (tag) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getTag());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt(1));
            }
        }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                this.closeStatement(preparedStatement);
            }
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
            this.closeStatement(statement);
        }
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tagovi = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet.next()) {

                Tag tag = new Tag(resultSet.getInt("id"), resultSet.getString("tag"));

                tagovi.add(tag);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tagovi;
    }

    @Override
    public List<Vest> tagVest(Integer id) {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vest JOIN vest_tag ON vest.id = vest_tag.vest_id JOIN tag ON tag.id = vest_tag.tag_id WHERE tag.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

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
