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
public class DPromocion {
 
     public static final String[] HEADERS = {"ID", "NOMBRE", "DESCUENTO"};

    private final DBConeccion connection;

    public DPromocion() {
        connection = new DBConeccion("postgres", "123", "127.0.0.1", "5432", "db_grupo02sc");
    }

    public void guardar(String nombre, int descuento) throws SQLException, ParseException {

        String query = "INSERT INTO promocion(nombre, descuento) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, descuento);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPromocion.java dice: "
                    + "Ocurrio un error al insertar un promocion guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String nombre, int descuento) throws SQLException, ParseException {
        
        String query = "UPDATE promocion SET nombre=?, descuento=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, descuento);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPromocion.java dice: "
                    + "Ocurrio un error al modificar un promocion modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM promocion WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPromocion.java dice: "
                    + "Ocurrio un error al eliminar un promocion eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> promociones = new ArrayList<>();
        String query = "SELECT * FROM promocion";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            promociones.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                String.valueOf(set.getInt("descuento")),
            });
        }
        return promociones;
    }

    public String[] ver(int id) throws SQLException {
        String[] promocion = null;
        String query = "SELECT * FROM promocion WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            promocion = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                String.valueOf(set.getInt("descuento")),
            };
        }
        return promocion;
    }

    public int getIdByNombre(String nombre) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM promocion WHERE nombre=?";
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
