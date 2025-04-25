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
public class DCategoria {

    public static final String[] HEADERS = {"ID", "NOMBRE"};

    private final DBConeccion connection;

    public DCategoria() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String nombre) throws SQLException {

        String query = "INSERT INTO categoria(nombre) values(?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCategoria.java dice: "
                    + "Ocurrio un error al insertar un genero guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String nombre) throws SQLException {
        
        String query = "UPDATE categoria SET nombre=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCategoria.java dice: "
                    + "Ocurrio un error al modificar un categoria modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM categoria WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DCategoria.java dice: "
                    + "Ocurrio un error al eliminar una categoria eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> categorias = new ArrayList<>();
        String query = "SELECT * FROM categoria";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            categorias.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
            });
        }
        return categorias;
    }

    public String[] ver(int id) throws SQLException {
        String[] categoria = null;
        String query = "SELECT * FROM categoria WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            categoria = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
            };
        }
        return categoria;
    }

    public int getIdByNombre(String nombre) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM categoria WHERE nombre=?";
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

