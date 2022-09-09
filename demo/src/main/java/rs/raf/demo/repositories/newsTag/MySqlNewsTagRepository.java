package rs.raf.demo.repositories.newsTag;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.NewsTag;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsTagRepository extends MySqlAbstractRepository implements NewsTagRepository {

    @Override
    public List<Tag> allTagsForNews(Integer id) {
        List<Tag> tags = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news_tag where news_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                keys.add(resultSet.getInt("tag_id")); // dodajem kljuceve od tag-ova koje zelim posle da uzmem iz tag tabele
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag"); // uzimam sve tagove iz tag tabele

            while(resultSet.next()){
                if(keys.contains(resultSet.getInt("tag_id"))){ // ako mi lista sa kljucevima sadzri tag_id
                    tags.add(new Tag(resultSet.getInt("tag_id"), resultSet.getString("tag")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
            this.closeStatement(statement);
        }
        return tags;
    }

    @Override
    public List<News> allNewsForTag(Integer id) {
        List<News> news = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news_tag where tag_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                keys.add(resultSet.getInt("news_id")); // dodajem kljuceve od vesti koje zelim posle da uzmem iz news tabele
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news"); // uzimam sve vesti iz news tabele

            while(resultSet.next()){
                if(keys.contains(resultSet.getInt("news_id"))){ // ako mi lista sa kljucevima sadzri news_id
                    news.add(new News(resultSet.getInt("news_id"), resultSet.getString("title"), resultSet.getString("content"),
                            resultSet.getString("date"), resultSet.getInt("visits"), resultSet.getInt("user_id"), resultSet.getInt("category_id")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
            this.closeStatement(statement);
        }
        return news;
    }

    @Override
    public NewsTag addNewsTag(NewsTag newsTag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

//            String[] generatedColumns = {"news_id"};

            preparedStatement = connection.prepareStatement("INSERT INTO news_tag (news_id, tag_id) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, newsTag.getNews_id());
            preparedStatement.setInt(2, newsTag.getTag_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println(e);
        } finally {
            this.closeStatement(preparedStatement);
            if(resultSet != null) {
                this.closeResultSet(resultSet);
            }
            this.closeConnection(connection);
        }

        return newsTag;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM news_tag where news_id = ?");
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
}
