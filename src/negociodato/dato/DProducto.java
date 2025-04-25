package negociodato.dato;

import database.DBConeccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class DProducto {
    
    public static final String[] HEADERS = 
    {"ID", "TITULO", "DESCRIPCION", "MARCA", "IMAGEN", "PRECIO", "STOCK", "ID_GENERO", "ID_PROMOCION"};

    private final DBConeccion connection;

    public DProducto() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String titulo, String descripcion, String marca, String imagen,
            int precio, int stock, int id_genero, int id_promocion) throws SQLException, ParseException {

        String query = "INSERT INTO producto(titulo, descripcion, autor, imagen, "
                + "precio, stock, id_genero, id_promocion)"
                + " values(?,?,?,?,?,?,?,NULLIF(?, 0))";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, titulo);
        ps.setString(2, descripcion);
        ps.setString(3, marca);
        ps.setString(4, imagen);
        ps.setInt(5, precio);
        ps.setInt(6, stock);
        ps.setInt(7, id_genero);
        ps.setInt(8, id_promocion);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProducto.java dice: "
                    + "Ocurrio un error al insertar un producto guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String titulo, String descripcion, String marca, String imagen,
            int precio, int stock, int id_genero, int id_promocion) throws SQLException, ParseException {
        
        String query = "UPDATE producto SET titulo=?, descripcion=?, autor=?,"
                + " imagen=?, precio=?, stock=?, id_genero=?, id_promocion=?  WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
         ps.setString(1, titulo);
        ps.setString(2, descripcion);
        ps.setString(3, marca);
        ps.setString(4, imagen);
        ps.setInt(5, precio);
        ps.setInt(6, stock);
        ps.setInt(7, id_genero);
        ps.setInt(8, id_promocion);
        ps.setInt(9, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProducto.java dice: "
                    + "Ocurrio un error al modificar un producto modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM producto WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DProducto.java dice: "
                    + "Ocurrio un error al eliminar un producto eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> productos = new ArrayList<>();
        String query = "SELECT * FROM producto";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            productos.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("titulo"),
                set.getString("descripcion"),
                set.getString("marca"),
                set.getString("imagen"),
                String.valueOf(set.getInt("precio")),
                String.valueOf(set.getInt("stock")),
                String.valueOf(set.getInt("id_genero")),
                String.valueOf(set.getInt("id_promocion")),
            });
        }
        return productos;
    }

    public String[] ver(int id) throws SQLException {
        String[] producto = null;
        String query = "SELECT * FROM producto WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            producto = new String[]{
                String.valueOf(set.getInt("id")),
                 set.getString("titulo"),
                set.getString("descripcion"),
                set.getString("marca"),
                set.getString("imagen"),
                String.valueOf(set.getInt("precio")),
                String.valueOf(set.getInt("stock")),
                String.valueOf(set.getInt("id_genero")),
                String.valueOf(set.getInt("id_promocion")),
            };
        }
        return producto;
    }

    public int getIdByTitulo(String email) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM producto WHERE titulo=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            id = set.getInt("id");
        }
        return id;
    }
    
    
    public List<String[]> graficaDeProductoMasVendido() throws SQLException {
        List<String[]> productos = new ArrayList<>();
        String query = "SELECT producto.titulo, sum(detalle_venta.total) as total, "
                + "count(*) as cantidad_comprada\n FROM detalle_venta JOIN producto ON\n"
                + "detalle_venta.id_producto = producto.id GROUP BY producto.titulo";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            productos.add(new String[]{
//                String.valueOf(set.getInt("id")),
                set.getString("titulo"),
                String.valueOf(set.getInt("total")),
                String.valueOf(set.getInt("cantidad_comprada")),
            });
        }
        return productos;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
