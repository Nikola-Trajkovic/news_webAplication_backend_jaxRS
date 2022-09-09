package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.News;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository{
    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"news_id"};

            preparedStatement = connection.prepareStatement("INSERT INTO news (title, content, date, visits, user_id, " +
                    "category_id)" +  " VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setString(3, news.getDate());
            preparedStatement.setInt(4, news.getVisits());
            preparedStatement.setInt(5, news.getUser_id());
            preparedStatement.setInt(6, news.getCategory_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setNews_id(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> allNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news");
            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("news_id"), resultSet.getString("title"), resultSet.getString("content"),
                        resultSet.getString("date"), resultSet.getInt("visits"), resultSet.getInt("user_id"), resultSet.getInt("category_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return news;
    }

    @Override
    public News findNews(Integer id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news where news_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int news_id = resultSet.getInt("news_id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String date = resultSet.getString("date");
                int visits = resultSet.getInt("visits");
                int user_id = resultSet.getInt("user_id");
                int category_id = resultSet.getInt("category_id");
                news = new News(news_id, title, content, date, visits, user_id, category_id);
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

        return news;
    }

    @Override
    public News incrementVisits(News news){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE news SET visits = ? where news_id = ?");
            preparedStatement.setInt(1, news.getVisits()+1);
            preparedStatement.setInt(2, news.getNews_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> findNewsByCategory(Integer id) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news where category_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("news_id"), resultSet.getString("title"), resultSet.getString("content"),
                        resultSet.getString("date"), resultSet.getInt("visits"), resultSet.getInt("user_id"), resultSet.getInt("category_id")));
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

        return news;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM news where news_id = ?");
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
    public void updateNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE news SET news.title = ?, " +
                    "news.content = ?, news.date = ?, news.visits = ?, news.user_id = ?, news.category_id = ? where news_id = ?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setString(3, news.getDate());
            preparedStatement.setInt(4, news.getVisits());
            preparedStatement.setInt(5, news.getUser_id());
            preparedStatement.setInt(6, news.getCategory_id());
            preparedStatement.setInt(7, news.getNews_id());
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
    public List<News> sortedNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news ORDER BY visits DESC LIMIT 10");
            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("news_id"), resultSet.getString("title"), resultSet.getString("content"),
                        resultSet.getString("date"), resultSet.getInt("visits"), resultSet.getInt("user_id"), resultSet.getInt("category_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return news;
    }
}
