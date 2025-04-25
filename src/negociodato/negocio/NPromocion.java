/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import negociodato.dato.DPromocion;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NPromocion {
    
    private final DPromocion dPromocion;
    
    public NPromocion() {
        this.dPromocion = new DPromocion();
    }
    
    public void guardar(List<String> parametros) throws SQLException, ParseException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }

        dPromocion.guardar(parametros.get(0), Integer.parseInt(parametros.get(1)));
        dPromocion.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dPromocion.modificar(Integer.parseInt(parametros.get(0)), parametros.get(1), 
                Integer.parseInt(parametros.get(2)));
        dPromocion.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dPromocion.eliminar(Integer.parseInt(parametros.get(0)));
        dPromocion.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> promociones = (ArrayList<String[]>) dPromocion.listar();
        dPromocion.desconectar();
        return promociones;
    }
    
    
}
