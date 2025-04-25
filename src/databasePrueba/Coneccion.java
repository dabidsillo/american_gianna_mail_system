
package databasePrueba;

import databasePrueba.DBConeccion;
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
            //conexion a la base de datos de tecno web
            DBConeccion sqlConnection =
                    new DBConeccion("grupo04sc", "grup004grup004", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
            // consulta de prueba a la db de tecnoweb de mi grupo
            String query = "SELECT * FROM comando WHERE id = 1"; // consulta
            PreparedStatement ps = sqlConnection.connect().prepareStatement(query); // preparada
            ResultSet rs = ps.executeQuery();  // ejecutada
            // imprimir el primer registro
            System.out.println("resultado: " + rs.next());
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("caso_uso"));
            System.out.println(rs.getString("accion"));
            System.out.println(rs.getString("parametro"));

        } catch (SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
