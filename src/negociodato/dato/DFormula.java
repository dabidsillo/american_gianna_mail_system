package negociodato.dato;

import databasePrueba.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DFormula {

    public static final String[] HEADERS = {"ID", "NOMBRE", "DESCRIPCION"};

    private final DBConeccion connection;

    public DFormula() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String nombre, String descripcion) throws SQLException {
        String query = "INSERT INTO formulas(nombre, descripcion) VALUES(?, ?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);

        if (ps.executeUpdate() == 0) {
            throw new SQLException("Error al guardar la fórmula");
        }
    }

    public void modificar(int id, String nombre, String descripcion) throws SQLException {
        String query = "UPDATE formulas SET nombre=?, descripcion=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            throw new SQLException("Error al modificar la fórmula");
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM formulas WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            throw new SQLException("Error al eliminar la fórmula");
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> formulas = new ArrayList<>();
        String query = "SELECT * FROM formulas";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            formulas.add(new String[]{
                String.valueOf(rs.getInt("id")),
                rs.getString("nombre"),
                rs.getString("descripcion")
            });
        }
        return formulas;
    }
    
    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
