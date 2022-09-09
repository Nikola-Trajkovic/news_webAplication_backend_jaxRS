package com.example.Projekat_web.repositories.komentar;

import com.example.Projekat_web.entities.Komentar;
import com.example.Projekat_web.repositories.MySqlAbstractRepository;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlKomentarRepository extends MySqlAbstractRepository implements KomentarInterface {
    @Override
    public List<Komentar> getKomentar(Integer id) {
        List<Komentar> komentari = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from komentar where fk_vest = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Komentar komentar = new Komentar(resultSet.getInt("id"), resultSet.getString("autor"), resultSet.getString("text"),
                        resultSet.getString("datum"),resultSet.getInt("fk_vest"));

                komentari.add(komentar);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentari;
    }

    @Override
    public Komentar addKomentar(Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            Timestamp timestamp = new Timestamp(new Date().getTime());

            preparedStatement = connection.prepareStatement("INSERT INTO komentar (autor , text, datum, fk_vest) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, komentar.getAutor());
            preparedStatement.setString(2, komentar.getText());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setInt(4, komentar.getFk_vest());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                komentar.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return komentar;
    }

}
