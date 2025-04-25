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
public class DDetalleVenta {
      public static final String[] HEADERS = {"ID", "CANTIDAD", "TOTAL", "ID_VENTA", "ID_PRODUCTO"};

    private final DBConeccion connection;

    public DDetalleVenta() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }
    
   public void guardar(int cantidad, int total, int id_venta, int id_producto) throws SQLException {
        String query = "INSERT INTO detalle_venta(cantidad, total, id_venta, id_producto)"
                + " values(?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, cantidad);
        ps.setInt(2, total);
        ps.setInt(3, id_venta);        
        ps.setInt(4, id_producto);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DDetalleVenta.java dice: "
                    + "Ocurrio un error al insertar un detalle_venta guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, int cantidad, int total, int id_venta, 
            int id_producto) throws SQLException {
        
        String query = "UPDATE detalle_venta SET cantidad=?, total=?, id_venta=?, "
                + "id_producto=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, cantidad);
        ps.setInt(2, total);
        ps.setInt(3, id_venta);        
        ps.setInt(4, id_producto);
        ps.setInt(5, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DDetalleVenta.java dice: "
                    + "Ocurrio un error al modificar un detalle_venta modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM detalle_venta WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DDetalleVenta.java dice: "
                    + "Ocurrio un error al eliminar un detalle_venta eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> detalles = new ArrayList<>();
        String query = "SELECT * FROM detalle_venta";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            detalles.add(new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("cantidad")),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("id_venta")),
                String.valueOf(set.getInt("id_producto")),
            });
        }
        return detalles;
    }

    public String[] ver(int id) throws SQLException {
        String[] detalle = null;
        String query = "SELECT * FROM detalle_venta WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            detalle = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("cantidad")),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("id_venta")),
                String.valueOf(set.getInt("id_producto")),
            };
        }
        return detalle;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
