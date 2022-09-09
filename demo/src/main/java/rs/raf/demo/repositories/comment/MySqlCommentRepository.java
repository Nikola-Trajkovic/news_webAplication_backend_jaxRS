package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"comment_id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comment (author, content, date, news_id) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setString(3, comment.getDate());
            preparedStatement.setInt(4, comment.getNews_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setComment_id(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allComments(Integer id) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null; // todo ovde promeniti na statement
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM comment where news_id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("comment_id"), resultSet.getString("author"),
                        resultSet.getString("content"), resultSet.getString("date")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public Comment findComment(Integer id) {
        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comment where comment_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int comment_id = resultSet.getInt("comment_id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                String date = resultSet.getString("date");
                comment = new Comment(comment_id, author, content, date);
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

        return comment;
    }
}
