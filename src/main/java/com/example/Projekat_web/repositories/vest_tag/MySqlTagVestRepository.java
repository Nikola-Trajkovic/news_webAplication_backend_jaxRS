package com.example.Projekat_web.repositories.vest_tag;

import com.example.Projekat_web.entities.VestTag;
import com.example.Projekat_web.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlTagVestRepository extends MySqlAbstractRepository implements VestTagInterface {
    @Override
    public List<VestTag> getVestTag() {
        return null;
    }

    @Override
    public VestTag addvestTag(VestTag vestTag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();



            preparedStatement = connection.prepareStatement("INSERT INTO vest_tag (vest_id, tag_id) VALUES(?, ?)");
            preparedStatement.setInt(1, vestTag.getVest_id());
            preparedStatement.setInt(2, vestTag.getTag_id());

            preparedStatement.executeUpdate();



        } catch (SQLException e) {

        } finally {
            if(preparedStatement != null) {
                this.closeStatement(preparedStatement);
            }
            if(resultSet != null) {
                this.closeResultSet(resultSet);
            }
            this.closeConnection(connection);
        }
        return vestTag;
    }
}
