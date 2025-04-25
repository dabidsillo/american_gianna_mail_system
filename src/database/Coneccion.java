
package database;

import database.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coneccion {
        public static void main(String[] args) {
        try {
            // username: postgres
            // password: 123
            DBConeccion sqlConnection =
                    new DBConeccion("postgres", "123", "127.0.0.1", "5432", "grupo02sc");
            String query = "SELECT * FROM usuario WHERE id = 1";
            PreparedStatement ps = sqlConnection.connect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("resultado: " + rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
