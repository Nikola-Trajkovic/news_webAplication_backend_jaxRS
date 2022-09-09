package com.example.Projekat_web.repositories.vest;


import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.repositories.MySqlAbstractRepository;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlVestRepository extends MySqlAbstractRepository implements VestInterface {
    @Override
    public List<Vest> getVest() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vest ORDER BY vreme_kreiranja DESC");
            while (resultSet.next()) {

                Vest vest = new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("text"),
                        resultSet.getString("vreme_kreiranja"), resultSet.getInt("broj_poseta"), resultSet.getInt("fk_korisnik"),
                        resultSet.getInt("fk_kategorija"));

                vesti.add(vest);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;

    }

    @Override
    public Vest addVest(Vest vest) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            Timestamp timestamp = new Timestamp(new Date().getTime());

            preparedStatement = connection.prepareStatement("INSERT INTO vest (naslov , text, vreme_kreiranja, broj_poseta, fk_korisnik, fk_kategorija) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, vest.getNaslov());
            preparedStatement.setString(2, vest.getText());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setInt(4, vest.getBrojPoseta());
            preparedStatement.setInt(5, vest.getFk_korisnik());
            preparedStatement.setInt(6, vest.getFk_kategorija());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;

    }

    @Override
    public List<Vest> getVestDate() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vest ORDER BY vreme_kreiranja DESC LIMIT 10");

            int br =0;

            while (resultSet.next()) {

                if(br != 10){
                    Vest vest = new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("text"),
                            resultSet.getString("vreme_kreiranja"), resultSet.getInt("broj_poseta"), resultSet.getInt("fk_korisnik"),
                            resultSet.getInt("fk_kategorija"));

                    vesti.add(vest);
                }else{
                    break;
                }

                br++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public List<Vest> getVestPosete() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vest ORDER BY broj_poseta DESC LIMIT 10");

            int br =0;

            while (resultSet.next()) {

                if(br != 10){
                    Vest vest = new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("text"),
                            resultSet.getString("vreme_kreiranja"), resultSet.getInt("broj_poseta"), resultSet.getInt("fk_korisnik"),
                            resultSet.getInt("fk_kategorija"));

                    vesti.add(vest);
                }else{
                    break;
                }

                br++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public Vest updateVest(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE vest SET broj_poseta = broj_poseta + 1 WHERE id = ?");
            preparedStatement.setInt(1, vest.getId());
            preparedStatement.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);

            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public Vest findVest(Integer id) {
        Vest vest = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vest WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {
                vest = new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("text"),
                        resultSet.getString("vreme_kreiranja"), resultSet.getInt("broj_poseta"), resultSet.getInt("fk_korisnik"),
                        resultSet.getInt("fk_kategorija"));
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public void deleteVest(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("DELETE FROM vest WHERE id = ?");
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
    public void updateVestDrugi(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE vest SET naslov = ?, text = ?, fk_kategorija = ? where id = ?");
            preparedStatement.setString(1, vest.getNaslov());
            preparedStatement.setString(2, vest.getText());
            preparedStatement.setInt(3, vest.getFk_kategorija());
            preparedStatement.setInt(4, vest.getId());

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
}
