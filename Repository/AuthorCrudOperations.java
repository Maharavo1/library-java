package Repository;
import ConnectionConfig.ConnectionConfiguration;
import Models.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorCrudOperations implements CrudOperations<Author> {
    private static Connection connection ;
    public static void getConnection(){
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        connection= connectionConfiguration.createConnection();
    }
    @Override
    public List<Author> findAll() {
        List<Author> allAuthors = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                allAuthors.add(new Author(
                        result.getInt("id"),
                        result.getString("authorName"),
                        result.getString("sex")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allAuthors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        String sql = "INSERT INTO author (id, authorName, sex) VALUES (?,?, ?)";
        List<Author> savedAuthors = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Author author : toSave) {
                    preparedStatement.setInt(1 , author.getId());
                    preparedStatement.setString(2, author.getAuthorName());
                    preparedStatement.setString(3, author.getSex());
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        savedAuthors.add(author);
                    }
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e) ;
        }
        return savedAuthors;
    }

    @Override
    public Author save(Author toSave) {
        String sql = "INSERT INTO author (id, authorName, sex) VALUES (?,?, ?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1 , toSave.getId());
                preparedStatement.setString(2, toSave.getAuthorName());
                preparedStatement.setString(3, toSave.getSex());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Author delete(Author toDelete) {
        String sql = "DELETE FROM author WHERE id = ?" ;
        try(PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setInt(1 , toDelete.getId());
            preparedStatement.executeUpdate();
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
