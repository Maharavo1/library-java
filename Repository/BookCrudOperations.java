package Repository;
import ConnectionConfig.ConnectionConfiguration;
import Models.Book;
import Models.Topic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements  CrudOperations<Book> {
    private static Connection connection ;
    public static void getConnection(){
    ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        connection= connectionConfiguration.createConnection();
    }
    @Override
    public List<Book> findAll() {
        List<Book> allBooks = new ArrayList<>();
        String sql = "SELECT * FROM \"book\" ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                allBooks.add(new Book(
                        result.getInt("id"),

                        result.getString("type"),
                        result.getString("bookName"),
                        result.getInt("pageNumbers"),
                        Topic.valueOf(result.getString("topic")),
                        result.getDate("releaseDate"),
                        result.getInt("authorId"),
                        result.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allBooks;
    }


    @Override
    public List<Book> saveAll(List<Book> toSave) {
        String sql = "INSERT INTO \"book\" (id, type, bookName, pageNumbers, topic, releaseDate, authorId,  status) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        List<Book> savedBooks = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Book book : toSave) {
                    preparedStatement.setInt(1 , book.getId());
                    preparedStatement.setString(2, book.getType());
                    preparedStatement.setString(3, book.getBookName());
                    preparedStatement.setInt(4, book.getPageNumbers());
                    preparedStatement.setObject(5, book.getTopic(), Types.OTHER);
                    preparedStatement.setDate(6, new java.sql.Date(book.getReleaseDate().getTime()));
                    preparedStatement.setInt(7 , book.getAuthorId());
                    preparedStatement.setString(8, book.getStatus());
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        savedBooks.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e) ;
        }
        return savedBooks;
    }

    @Override
    public Book save(Book toSave) {
        String sql = "INSERT INTO book (type, bookName, pageNumbers, topic, releaseDate, status) VALUES (?, ?, ?, ?, ?, ?) RETURNING *";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, toSave.getType());
                preparedStatement.setString(2, toSave.getBookName());
                preparedStatement.setInt(3, toSave.getPageNumbers());
                preparedStatement.setObject(4, toSave.getTopic(), Types.OTHER);
                preparedStatement.setDate(5, new java.sql.Date(toSave.getReleaseDate().getTime()));
                preparedStatement.setString(6, toSave.getStatus());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Book delete(Book toDelete) {
        String sql = "DELETE FROM book WHERE id = ?" ;
        try(PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setInt(1 , toDelete.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
