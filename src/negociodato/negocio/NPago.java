/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DPago;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NPago {
    private final DPago dPago;
    
    public NPago() {
        this.dPago = new DPago();
    }
    
     public void guardar(List<String> parametros) throws SQLException, ParseException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        dPago.guardar(parametros.get(0), parametros.get(1), parametros.get(2),
                parametros.get(3), Integer.parseInt(parametros.get(4)) );

        dPago.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException {
         if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
         
        dPago.modificar(Integer.parseInt(parametros.get(0)), parametros.get(1),
                parametros.get(2), parametros.get(3), parametros.get(4), 
                Integer.parseInt(parametros.get(5)) );
        
        dPago.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dPago.eliminar(Integer.parseInt(parametros.get(0)));
        dPago.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> pagos = (ArrayList<String[]>) dPago.listar();
        dPago.desconectar();
        return pagos;
    }
    
    public ArrayList<String[]> graficaTiposDePagoMasUtilizados() throws SQLException {
        ArrayList<String[]> pagos = (ArrayList<String[]>) dPago.graficaTiposDePagoMasUtilizados();
        dPago.desconectar();
        return pagos;
    }
}
