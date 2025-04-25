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
import negociodato.utils.DateString;

/**
 *
 * @author DAVID, ELMER
 */
public class DPago {

    public static final String[] HEADERS = {"ID", "TIPO", "FECHA", "HORA", "DETALLE", "ID_VENTA"};

    private final DBConeccion connection;

    public DPago() {
        connection = new DBConeccion("grupo04sc", "grup004grup004*", "mail.tecnoweb.org.bo", "5432", "db_grupo04sc");
    }

    public void guardar(String tipo, String fecha, String hora, String detalle,
            int id_venta) throws SQLException, ParseException {
        System.out.println(tipo);
        System.out.println(DateString.StringToDateSQL(fecha));
        System.out.println(hora);
        System.out.println(detalle);
        System.out.println(id_venta);
        String query = "INSERT INTO pago(tipo, fecha, hora, detalle, id_venta) values(?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, tipo);
        ps.setDate(2, DateString.StringToDateSQL(fecha));
        ps.setString(3, hora);
        ps.setString(4, detalle);
        ps.setInt(5, id_venta);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPago.java dice: "
                    + "Ocurrio un error al insertar un pago guardar()");
            throw new SQLException();
        }
    }

    public void modificar(int id, String tipo, String fecha, String hora,
            String detalle, int id_venta) throws SQLException, ParseException {

        String query = "UPDATE pago SET tipo=?, fecha=?, hora=?, detalle=?,"
                + "id_venta=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, tipo);
        ps.setDate(2, DateString.StringToDateSQL(fecha));
        ps.setString(3, hora);
        ps.setString(4, detalle);
        ps.setInt(5, id_venta);
        ps.setInt(6, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPago.java dice: "
                    + "Ocurrio un error al modificar un pago modificar()");
            throw new SQLException();
        }
    }

    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM pago WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPago.java dice: "
                    + "Ocurrio un error al eliminar un pago eliminar()");
            throw new SQLException();
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT * FROM pago";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            pagos.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("tipo"),
                set.getString("fecha"),
                set.getString("hora"),
                set.getString("detalle"),
                String.valueOf(set.getInt("id_venta")),});
        }
        return pagos;
    }

    public String[] ver(int id) throws SQLException {
        String[] pago = null;
        String query = "SELECT * FROM pago WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            pago = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("tipo"),
                set.getString("fecha"),
                set.getString("hora"),
                set.getString("detalle"),
                String.valueOf(set.getInt("id_venta")),};
        }
        return pago;
    }

    public List<String[]> graficaTiposDePagoMasUtilizados() throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT tipo, COUNT(*) as cantidad FROM pago\n"
                + "GROUP BY tipo";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            pagos.add(new String[]{
//                String.valueOf(set.getInt("id")),
                set.getString("tipo"),
                String.valueOf(set.getInt("cantidad")),
            });
        }
        return pagos;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
