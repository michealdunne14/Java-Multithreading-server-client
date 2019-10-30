/*
 * @author Micheal Dunne
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
    //	User name
    private final String userName = "root";
    //	password
    private final String password = "";
    //	server name
    private final String serverName = "localhost";
    //	port number
    private final int portNumber = 3306;

    private final String dbName = "assign2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT";

    //  Connect to MySql Database
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://"
                        + this.serverName + ":" + this.portNumber + "/" + this.dbName,
                connectionProps);

        return conn;
    }
}
