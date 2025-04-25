/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.dato;

import database.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  DAVID, ELMER
 */
public class DCliente {
    public static final String[] HEADERS =  
    {"ID", "EMAIL", "PASSWORD", "NOMBRE", "TELEFONO", "DIRECCION", "CI"};

    private final DBConeccion connection;

    public DCliente() {
      
         connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");

    }

    public void guardar(int id, String ci) throws SQLException {

        String query = "INSERT INTO cliente(id, ci) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, ci);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCliente.java dice: "
                    + "Ocurrio un error al insertar un cliente guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String ci) throws SQLException, ParseException {
        
        String query = "UPDATE cliente SET ci=?  WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, ci);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCliente.java dice: "
                    + "Ocurrio un error al modificar un cliente modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM cliente WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCliente.java dice: "
                    + "Ocurrio un error al eliminar un cliente eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT usuario.id, usuario.email, usuario.password, usuario.nombre,"
                + "usuario.telefono, usuario.direccion, cliente.ci FROM usuario JOIN cliente ON "
                + "usuario.id = cliente.id";
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
                set.getString("ci"),
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
