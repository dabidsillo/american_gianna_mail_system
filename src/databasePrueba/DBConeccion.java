
package databasePrueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConeccion {
        
    private static final String DRIVER = "jdbc:postgresql://";
    
    private Connection connection;
    private final String user;
    private final String password; //root
    private final String host;
    private final String port;
    private final String name;
    private final String url;

    public DBConeccion(String user, String password, String host, String port, String name) {
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        this.name = name;        
        this.url = DRIVER + host + ":" + port + "/" + name; //127.0.0.1:5432/db_grupo02sc
    }
    
    public Connection connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("Class SqlConnection.java dice: "
                    + "Ocurrio un error al momento de establecer una conexion connect()");
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Class SqlConnection.java dice:"
                    + "Ocurrio un error al momento de cerrar la conexion closeConnection()");
        }
    }
}



