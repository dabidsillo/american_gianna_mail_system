/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import negociodato.dato.DCategoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAVID, ELMER
 */
public class NCategoria {
    
    private DCategoria dCategoria;
    
    public NCategoria() {
        this.dCategoria = new DCategoria();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dCategoria.guardar(parametros.get(0));
        dCategoria.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dCategoria.modificar(Integer.parseInt(parametros.get(0)), parametros.get(1));
        dCategoria.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dCategoria.eliminar(Integer.parseInt(parametros.get(0)));
        dCategoria.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> categorias = (ArrayList<String[]>) dCategoria.listar();
        dCategoria.desconectar();
        return categorias;
    }
    
}
