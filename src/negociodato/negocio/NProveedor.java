/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DProveedor;
import negociodato.dato.DUsuario;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NProveedor {
     private final DUsuario dUsuario;
    private final DProveedor dProveedor;
    
    public NProveedor() {
        this.dUsuario = new DUsuario();
        this.dProveedor = new DProveedor();
    }
    
     public void guardar(List<String> parametros) throws SQLException {
         
        int idUsuario = dUsuario.getIdByEmail(parametros.get(1));
        
        // verifica que el email se encuentra disponible
         if (idUsuario == -1) {
            dUsuario.guardar(parametros.get(0), parametros.get(1), parametros.get(2),
                parametros.get(3), parametros.get(4));
            
            int idUsuarioGuardado = dUsuario.getIdByEmail(parametros.get(0));
            dProveedor.guardar(idUsuarioGuardado, parametros.get(5), parametros.get(6));
         }

        dUsuario.desconectar();
        dProveedor.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException {

        int idUsuario = dUsuario.getIdByEmail(parametros.get(0));
        int idUsuarioParametro = Integer.parseInt(parametros.get(0)); //id
        
        // verifica que el email este disponible
        if (idUsuario == -1 || (idUsuario == idUsuarioParametro)) {
            dUsuario.modificar(idUsuarioParametro, parametros.get(1), 
                parametros.get(2), parametros.get(3), parametros.get(4), parametros.get(5));
            
            dProveedor.modificar(idUsuarioParametro, parametros.get(6),parametros.get(7));
        }
        
        dUsuario.desconectar();
        dProveedor.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dProveedor.eliminar(Integer.parseInt(parametros.get(0)));
        dUsuario.eliminar(Integer.parseInt(parametros.get(0)));
        
        dUsuario.desconectar();
        dProveedor.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> usuarios = (ArrayList<String[]>) dProveedor.listar();
        dProveedor.desconectar();
        return usuarios;
    }
    
}
