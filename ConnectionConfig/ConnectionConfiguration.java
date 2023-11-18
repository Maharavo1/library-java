package ConnectionConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {
    private String URL ;
    private String PASS;
    private String USER ;

    public ConnectionConfiguration() {
        this.URL = "jdbc:postgresql://localhost/"+System.getenv("DB_URL");
        this.USER = System.getenv("DB_USER");
        this.PASS = System.getenv("DB_PASSWORD");
    }
    public Connection createConnection(){
        try{
            Connection connection = DriverManager.getConnection(
                    this.URL , this.USER ,this.PASS
            );
            return connection ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
