package negociodato.dato;

import database.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DUsuario {

    public static final String[] HEADERS = 
    {"ID", "EMAIL", "PASSWORD", "NOMBRE", "TELEFONO", "DIRECCION"};

    private final DBConeccion connection;

    public DUsuario() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String email, String password, String nombre, String telefono,
            String direccion) throws SQLException {

        String query = "INSERT INTO usuario(email, password, nombre, telefono, direccion)"
                + " values(?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        ps.setString(3, nombre);
        ps.setString(4, telefono);
        ps.setString(5, direccion);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DUsuario.java dice: "
                    + "Ocurrio un error al insertar un usuario guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String email, String password, String nombre, String telefono,
            String direccion) throws SQLException, ParseException {
        
        String query = "UPDATE usuario SET email=?, password=?, nombre=?,"
                + " telefono=?, direccion=?  WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        ps.setString(3, nombre);
        ps.setString(4, telefono);
        ps.setString(5, direccion);
        ps.setInt(6, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DUsuario.java dice: "
                    + "Ocurrio un error al modificar un usuario modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM usuario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DUsuario.java dice: "
                    + "Ocurrio un error al eliminar un usuario eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            usuarios.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("email"),
                set.getString("password"),
                set.getString("nombre"),
                set.getString("telefono"),
                set.getString("direccion"),
            });
        }
        return usuarios;
    }

    public String[] ver(int id) throws SQLException {
        String[] usuario = null;
        String query = "SELECT * FROM usuario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            usuario = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("email"),
                set.getString("password"),
                set.getString("nombre"),
                set.getString("telefono"),
                set.getString("direccion"),
            };
        }
        return usuario;
    }

    public int getIdByEmail(String email) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM usuario WHERE email=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);

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
