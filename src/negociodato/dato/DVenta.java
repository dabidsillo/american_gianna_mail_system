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
public class DVenta {
    public static final String[] HEADERS = {"ID", "FECHA", "HORA", "TOTAL", "ID_CLIENTE", "ID_SERVICIO"};

    private final DBConeccion connection;

    public DVenta() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }
    
   public void guardar(int total, int id_cliente, int id_servicio) throws SQLException {

        String query = "INSERT INTO venta(total, id_cliente, id_servicio)"
                + " values(?,?,NULLIF(?, 0))";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, total);
        ps.setInt(2, id_cliente);
        ps.setInt(3, id_servicio);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DVenta.java dice: "
                    + "Ocurrio un error al insertar un venta guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, int total, int id_cliente, 
            int id_servicio) throws SQLException, ParseException {
        
        String query = "UPDATE servicio SET total=?, id_cliente=?, id_servicio=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, total);
        ps.setInt(2, id_cliente);
        ps.setInt(3, id_servicio);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DVenta.java dice: "
                    + "Ocurrio un error al modificar un venta modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM venta WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DVenta.java dice: "
                    + "Ocurrio un error al eliminar un venta eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT * FROM venta";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            ventas.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("fecha"),
                set.getString("hora"),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("id_cliente")),
                String.valueOf(set.getInt("id_servicio")),
            });
        }
        return ventas;
    }

    public String[] ver(int id) throws SQLException {
        String[] venta = null;
        String query = "SELECT * FROM venta WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            venta = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("fecha"),
                set.getString("hora"),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("id_cliente")),
                String.valueOf(set.getInt("id_servicio")),
            };
        }
        return venta;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }

}
