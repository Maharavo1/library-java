package Repository;
import ConnectionConfig.ConnectionConfiguration;
import Models.Subscribers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudOperations implements  CrudOperations<Subscribers> {
    private static Connection connection ;
    public static void getConnection(){
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        connection= connectionConfiguration.createConnection();
    }
    @Override
    public List<Subscribers> findAll() {
        List<Subscribers> allSubscribers = new ArrayList<>();
        String sql = "SELECT * FROM subscribers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                allSubscribers.add(new Subscribers(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("reference")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allSubscribers;
    }

    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {
        String sql = "INSERT INTO subscribers (id, name, reference) VALUES (?,?, ?)";
        List<Subscribers> savedSubscribers = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Subscribers subscriber : toSave) {
                    preparedStatement.setInt(1 ,subscriber.getId());
                    preparedStatement.setString(2, subscriber.getName());
                    preparedStatement.setString(3, subscriber.getReference());
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        savedSubscribers.add(subscriber);
                    }
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e) ;
        }
        return savedSubscribers;
    }

    @Override
    public Subscribers save(Subscribers toSave) {
        String query = "INSERT INTO subscribers (id, name, reference) VALUES (?,?,?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1 ,toSave.getId());
                preparedStatement.setString(2, toSave.getName());
                preparedStatement.setString(3, toSave.getReference());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public Subscribers delete(Subscribers toDelete) {
        String sql = "DELETE FROM subscribers WHERE id = ?" ;
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
