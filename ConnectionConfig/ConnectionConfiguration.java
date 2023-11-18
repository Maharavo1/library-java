package ConnectionConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {
    private String URL ;
    private String PASS;
    private String USER ;

    public ConnectionConfiguration() {
        this.URL = "jdbc:postgresql://localhost/library_management";
        this.USER = "postgres";
        this.PASS = "root";
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
