/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DInventario;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NInventario {
        
    private DInventario dInventario;
    
    public NInventario() {
        this.dInventario = new DInventario();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dInventario.guardar(parametros.get(0), Integer.parseInt(parametros.get(1)), 
                Integer.parseInt(parametros.get(2)));
        dInventario.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dInventario.modificar(Integer.parseInt(parametros.get(0)), parametros.get(1), 
                Integer.parseInt(parametros.get(2)), Integer.parseInt(parametros.get(3)));
        dInventario.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dInventario.eliminar(Integer.parseInt(parametros.get(0)));
        dInventario.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> inventarios = (ArrayList<String[]>) dInventario.listar();
        dInventario.desconectar();
        return inventarios;
    }
    
}
