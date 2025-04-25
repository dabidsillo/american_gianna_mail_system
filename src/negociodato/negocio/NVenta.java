/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DCliente;
import negociodato.dato.DDetalleVenta;
import negociodato.dato.DServicio;
import negociodato.dato.DVenta;

/**
 *
 * @author RYAN RAMIREZ PINEDA
 */
public class NVenta {
    private final DVenta dVenta;
    private final DServicio dServicio;
    private final DCliente dCliente;
    
    
    public NVenta() {
        this.dVenta = new DVenta();
        this.dServicio = new DServicio();
        this.dCliente = new DCliente();
    }
    
     public void guardar(List<String> parametros) throws SQLException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
                 
        if (parametros.size() < 3) {
            //si no envio un servicio, se le agrega el parametro en 0
            parametros.add("0");
        }
        
        // verificar que el cliente y servicio existan (si no envio un servivio se omite)
        int id_cliente =  dCliente.getIdByEmail(parametros.get(1));
        int id_servicio = "0".equals(parametros.get(2)) ? 0 : dServicio.getIdByNombre(parametros.get(2)) ;
        
         if (id_cliente != -1  && (id_servicio != -1 || id_servicio == 0)) {
            dVenta.guardar(Integer.parseInt(parametros.get(0)), id_cliente, id_servicio);
         }

        dVenta.desconectar();
        dCliente.desconectar();
        dServicio.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
       if (parametros.size() < 4) {
            //si no envio un servicio, se le agrega el parametro en 0
            parametros.add("0");
        }
       
        // verificar que el cliente y servicio existan (si no envio un servivio se omite)
        int id_cliente =  dCliente.getIdByEmail(parametros.get(2));
        int id_servicio = Integer.parseInt(parametros.get(3)) == 0 ? 0 : dServicio.getIdByNombre(parametros.get(3)) ;
        
        if (id_cliente != -1  && (id_servicio != -1 || id_servicio == 0)) {
            dVenta.modificar(Integer.parseInt(parametros.get(0)), Integer.parseInt(parametros.get(1)),
                    id_cliente, id_servicio);
         }
        
        dVenta.desconectar();
        dCliente.desconectar();
        dServicio.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dVenta.eliminar(Integer.parseInt(parametros.get(0)));
        dVenta.desconectar();
        dCliente.desconectar();
        dServicio.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> ventas = (ArrayList<String[]>) dVenta.listar();
        dVenta.desconectar();
        dCliente.desconectar();
        dServicio.desconectar();
        return ventas;
    }
    
}
