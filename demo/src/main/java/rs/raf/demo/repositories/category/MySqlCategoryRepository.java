package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryRepository extends MySqlAbstractRepository implements CategoryRepository {

    @Override
    public Category addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"category_id"};

            preparedStatement = connection.prepareStatement("INSERT INTO category (name, description) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                category.setCategory_id(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    @Override
    public List<Category> allCategories() {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from category");
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("category_id"), resultSet.getString("name"), resultSet.getString("description")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return categories;
    }

    @Override
    public Category findCategory(Integer id) {
        Category category = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM category where category_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int category_id = resultSet.getInt("category_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                category = new Category(category_id, name, description);
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

        return category;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        boolean delete = true;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news");
            while (resultSet.next()) {
                if(resultSet.getInt("category_id") == id){ // ukoliko zelim da obrisem neku kategoriju, a neka vest se nalazi u toj kategoriji
                    delete = false;
                }
            }

            if(delete) {
                preparedStatement = connection.prepareStatement("DELETE FROM category where category_id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }

            if(preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                this.closeStatement(preparedStatement);
            }
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }
    }

    @Override
    public void update(Category category){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE category SET category.name = ?, " +
                    "category.description = ? where category_id = ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getCategory_id());
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
