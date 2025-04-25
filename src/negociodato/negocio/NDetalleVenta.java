/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DDetalleVenta;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NDetalleVenta {
    private final DDetalleVenta dDetalleVenta;
    
    public NDetalleVenta() {
        this.dDetalleVenta = new DDetalleVenta();
    }
    
     public void guardar(List<String> parametros) throws SQLException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dDetalleVenta.guardar(Integer.parseInt(parametros.get(0)), Integer.parseInt(parametros.get(1)),
                Integer.parseInt(parametros.get(2)), Integer.parseInt(parametros.get(3)));

        dDetalleVenta.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dDetalleVenta.modificar(Integer.parseInt(parametros.get(0)), Integer.parseInt(parametros.get(1)),
                Integer.parseInt(parametros.get(2)), Integer.parseInt(parametros.get(3)),
                Integer.parseInt(parametros.get(4)));
        
        dDetalleVenta.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dDetalleVenta.eliminar(Integer.parseInt(parametros.get(0)));
        dDetalleVenta.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> detalles = (ArrayList<String[]>) dDetalleVenta.listar();
        dDetalleVenta.desconectar();
        return detalles;
    }
}
