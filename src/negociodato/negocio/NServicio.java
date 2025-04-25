/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DServicio;

/**
 *
 * @author RYAN RAMIREZ PINEDA
 */
public class NServicio {
        private final DServicio dServicio;
    
    public NServicio() {
        this.dServicio = new DServicio();
    }
    
     public void guardar(List<String> parametros) throws SQLException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
         // verificar que el nombre este disponible 
        int idServicio = dServicio.getIdByNombre(parametros.get(0));
        
        if (idServicio == -1) {
            dServicio.guardar(parametros.get(0), Integer.parseInt(parametros.get(1)));
         }

        dServicio.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        int id_servicio_parametro = Integer.parseInt(parametros.get(0));
        int id_servicio = dServicio.getIdByNombre(parametros.get(1));

        // verificar que el nombre este disponible 
        if (id_servicio == -1 || (id_servicio == id_servicio_parametro)) {
            System.out.println("entro aqui");
            dServicio.modificar(id_servicio_parametro, parametros.get(1), 
                    Integer.parseInt(parametros.get(2)));
         }

        dServicio.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dServicio.eliminar(Integer.parseInt(parametros.get(0)));
        dServicio.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> servicios = (ArrayList<String[]>) dServicio.listar();
        dServicio.desconectar();
        return servicios;
    }
}
