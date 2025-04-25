
package negociodato.dato;

import databasePrueba.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DInsumo {
    public static final String[] HEADERS = {"ID", "NOMBRE", "CANTIDAD", "PRECIO"};
    private final DBConeccion connection;

    public DInsumo() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String nombre, int cantidad, double precio) throws SQLException {
        String query = "INSERT INTO insumo(nombre, cantidad, precio) VALUES(?, ?, ?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, cantidad);
        ps.setDouble(3, precio);
        if (ps.executeUpdate() == 0) {
            throw new SQLException("Error al insertar insumo");
        }
    }

    public void modificar(int id, String nombre, int cantidad, double precio) throws SQLException {
        String query = "UPDATE insumo SET nombre=?, cantidad=?, precio=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, cantidad);
        ps.setDouble(3, precio);
        ps.setInt(4, id);
        if (ps.executeUpdate() == 0) {
            throw new SQLException("Error al modificar insumo");
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM insumo WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            throw new SQLException("Error al eliminar insumo");
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> insumos = new ArrayList<>();
        String query = "SELECT * FROM insumo";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            insumos.add(new String[]{
                String.valueOf(rs.getInt("id")),
                rs.getString("nombre"),
                String.valueOf(rs.getInt("cantidad")),
                String.valueOf(rs.getDouble("precio"))
            });
        }
        return insumos;
    }

    public void desconectar() {
        connection.closeConnection();
    }
}
