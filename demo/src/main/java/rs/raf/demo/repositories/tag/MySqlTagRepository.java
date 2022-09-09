package rs.raf.demo.repositories.tag;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository {

    @Override
    public Tag addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;

        boolean check = true;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet.next()) {
                if(tag.getTag().equals(resultSet.getString("tag"))){
                    tag.setTag_id(resultSet.getInt("tag_id"));
                    check = false; // ukoliko vec imam u tabeli tag sa istim nazivom, onda ne zelim opet da dodajem taj isti tag
                }
            }

            if(check){
                String[] generatedColumns = {"tag_id"};

                preparedStatement = connection.prepareStatement("INSERT INTO tag (tag) VALUES(?)", generatedColumns);
                preparedStatement.setString(1, tag.getTag());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    tag.setTag_id(resultSet.getInt(1));
                }

            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            resultSet.close();
            connection.close();
            statement.close();
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
    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("tag_id"), resultSet.getString("tag")));
            }


            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public Tag findTag(Integer id) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag where tag_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int tag_id = resultSet.getInt("tag_id");
                String tagStr = resultSet.getString("tag");
                tag = new Tag(tag_id, tagStr);
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

        return tag;
    }
}
