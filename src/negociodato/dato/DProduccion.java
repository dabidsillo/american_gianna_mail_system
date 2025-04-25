package negociodato.dato;

import databasePrueba.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DProduccion {

    public static final String[] HEADERS = {"ID", "NOMBRE", "DESCRIPCION"};

    private final DBConeccion connection;

    public DProduccion() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String nombre, String descripcion) throws SQLException {
        String query = "INSERT INTO produccion(nombre, descripcion) values(?, ?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProduccion.java dice: Ocurrio un error al insertar una produccion guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String nombre, String descripcion) throws SQLException {
        String query = "UPDATE produccion SET nombre=?, descripcion=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProduccion.java dice: Ocurrio un error al modificar una produccion modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM produccion WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProduccion.java dice: Ocurrio un error al eliminar una produccion eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> producciones = new ArrayList<>();
        String query = "SELECT * FROM produccion";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            producciones.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("descripcion"),
            });
        }
        return producciones;
    }

    public String[] ver(int id) throws SQLException {
        String[] produccion = null;
        String query = "SELECT * FROM produccion WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            produccion = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("descripcion"),
            };
        }
        return produccion;
    }

    public int getIdByNombre(String nombre) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM produccion WHERE nombre=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            id = set.getInt("id");
        }
        return id;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
