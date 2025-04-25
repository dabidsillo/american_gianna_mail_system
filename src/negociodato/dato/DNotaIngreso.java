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
public class DNotaIngreso {
    public static final String[] HEADERS = 
    {"ID", "CANTIDAD", "FECHA", "COSTO", "TOTAL", "ID_INVENTARIO", "ID_PROVEEDOR", "ID_PERSONAL"};

    private final DBConeccion connection;

    public DNotaIngreso() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }
    
     public void guardar(int cantidad, int costo, int total, int id_inventario, 
             int id_proveedor, int id_personal) throws SQLException {

        String query = "INSERT INTO nota_ingreso(cantidad, costo, total, "
                + "id_inventario, id_proveedor, id_personal) values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, cantidad);
        ps.setInt(2, costo);
        ps.setInt(3, total);
        ps.setInt(4, id_inventario);
        ps.setInt(5, id_proveedor);
        ps.setInt(6, id_personal);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DNotaIngreso.java dice: "
                    + "Ocurrio un error al insertar un nota_ingreso guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, int cantidad, int costo, int total, int id_inventario, 
             int id_proveedor, int id_personal) throws SQLException {
        
        String query = "UPDATE inventario SET nombre=?, cantidad_disponible=?, id_producto=?, "
                + "id_personal=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, cantidad);
        ps.setInt(2, costo);
        ps.setInt(3, total);
        ps.setInt(4, id_inventario);
        ps.setInt(5, id_proveedor);
        ps.setInt(6, id_personal);
        ps.setInt(7, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DNotaIngreso.java dice: "
                    + "Ocurrio un error al modificar un nota_ingreso modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM nota_ingreso WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DInventario.java dice: "
                    + "Ocurrio un error al eliminar un inventario eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> notas = new ArrayList<>();
        String query = "SELECT * FROM nota_ingreso";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            notas.add(new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("cantidad")),
                set.getString("fecha"),
                String.valueOf(set.getInt("costo")),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("id_inventario")),
                String.valueOf(set.getInt("id_proveedor")),
                String.valueOf(set.getInt("id_personal")),
            });
        }
        return notas;
    }

    public String[] ver(int id) throws SQLException {
        String[] nota = null;
        String query = "SELECT * FROM nota_ingreso WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            nota = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("cantidad")),
                set.getString("fecha"),
                String.valueOf(set.getInt("costo")),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("id_inventario")),
                String.valueOf(set.getInt("id_proveedor")),
                String.valueOf(set.getInt("id_personal")),
            };
        }
        return nota;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
    
}
