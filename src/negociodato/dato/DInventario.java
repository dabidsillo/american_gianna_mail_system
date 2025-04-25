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
public class DInventario {
    public static final String[] HEADERS = {"ID", "NOMBRE", "CANTIDAD_DISPONIBLE", "ID_PRODUCTO"};

    private final DBConeccion connection;

    public DInventario() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }
    
    public void guardar(String nombre, int cantidad_disponible, int id_producto) throws SQLException {

        String query = "INSERT INTO inventario(nombre, cantidad_disponible, id_producto)"
                + " values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, cantidad_disponible);
        ps.setInt(3, id_producto);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DInventario.java dice: "
                    + "Ocurrio un error al insertar un inventario guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String nombre, int cantidad_disponible, 
            int id_producto) throws SQLException, ParseException {
        
        String query = "UPDATE inventario SET nombre=?, cantidad_disponible=?, id_producto=?"
                + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, cantidad_disponible);
        ps.setInt(3, id_producto);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DInventario.java dice: "
                    + "Ocurrio un error al modificar un inventario modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM inventario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DInventario.java dice: "
                    + "Ocurrio un error al eliminar un inventario eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> inventarios = new ArrayList<>();
        String query = "SELECT * FROM inventario";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            inventarios.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                String.valueOf(set.getInt("cantidad_disponible")),
                String.valueOf(set.getInt("id_producto")),
            });
        }
        return inventarios;
    }

    public String[] ver(int id) throws SQLException {
        String[] inventario = null;
        String query = "SELECT * FROM inventario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            inventario = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                String.valueOf(set.getInt("cantidad_disponible")),
                String.valueOf(set.getInt("id_producto")),
            };
        }
        return inventario;
    }

    public int getIdByNombre(String nombre) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM inventario WHERE nombre=?";
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
