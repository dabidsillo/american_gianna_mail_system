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
 * @author DAVID, ELMER
 */
public class DServicio {
    public static final String[] HEADERS = {"ID", "NOMBRE", "PRECIO"};

    private final DBConeccion connection;

    public DServicio() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }
    
   public void guardar(String nombre, int precio) throws SQLException {

        String query = "INSERT INTO servicio(nombre, precio) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, precio);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DServicio.java dice: "
                    + "Ocurrio un error al insertar un servicio guardar()");
            throw new SQLException();
        } 

    }

    public void modificar(int id, String nombre, int precio) throws SQLException {
        
        String query = "UPDATE servicio SET nombre=?, precio=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, precio);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DServicio.java dice: "
                    + "Ocurrio un error al modificar un servicio modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM servicio WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DServicio.java dice: "
                    + "Ocurrio un error al eliminar un servicio eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> servicios = new ArrayList<>();
        String query = "SELECT * FROM servicio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            servicios.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                String.valueOf(set.getInt("precio")),
            });
        }
        return servicios;
    }

    public String[] ver(int id) throws SQLException {
        String[] servicio = null;
        String query = "SELECT * FROM servicio WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            servicio = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                String.valueOf(set.getInt("precio")),
            };
        }
        return servicio;
    }

    public int getIdByNombre(String nombre) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM servicio WHERE nombre=?";
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
