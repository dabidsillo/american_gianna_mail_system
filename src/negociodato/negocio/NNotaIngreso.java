/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DNotaIngreso;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NNotaIngreso {
    private DNotaIngreso dNotaIngreso;
    
    public NNotaIngreso() {
        this.dNotaIngreso = new DNotaIngreso();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dNotaIngreso.guardar(Integer.parseInt(parametros.get(0)), Integer.parseInt(parametros.get(1)), 
                Integer.parseInt(parametros.get(2)), Integer.parseInt(parametros.get(3)), 
                Integer.parseInt(parametros.get(4)), Integer.parseInt(parametros.get(5)));
        dNotaIngreso.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dNotaIngreso.modificar(Integer.parseInt(parametros.get(0)), Integer.parseInt(parametros.get(1)), 
                Integer.parseInt(parametros.get(2)), Integer.parseInt(parametros.get(3)), 
                Integer.parseInt(parametros.get(4)), Integer.parseInt(parametros.get(5)),
                Integer.parseInt(parametros.get(6)));
        dNotaIngreso.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dNotaIngreso.eliminar(Integer.parseInt(parametros.get(0)));
        dNotaIngreso.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> notas = (ArrayList<String[]>) dNotaIngreso.listar();
        dNotaIngreso.desconectar();
        return notas;
    }
}
