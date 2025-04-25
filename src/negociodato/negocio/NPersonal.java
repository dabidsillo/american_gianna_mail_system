/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DPersonal;
import negociodato.dato.DUsuario;

/**
 *
 * @author RAMIREZ PINEDA
 */
public class NPersonal {
    private final DUsuario dUsuario;
    private final DPersonal dPersonal;
    
    public NPersonal() {
        this.dUsuario = new DUsuario();
        this.dPersonal = new DPersonal();
    }
    
     public void guardar(List<String> parametros) throws SQLException {
         
        int idUsuario = dUsuario.getIdByEmail(parametros.get(0));
        // verifica que el email se encuentra disponible
         if (idUsuario == -1) {
            dUsuario.guardar(parametros.get(0), parametros.get(1), parametros.get(2),
                parametros.get(3), parametros.get(4));
            
            int idUsuarioGuardado = dUsuario.getIdByEmail(parametros.get(0));
            dPersonal.guardar(idUsuarioGuardado, parametros.get(5));
         }

        dUsuario.desconectar();
        dPersonal.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException {

        int idUsuario = dUsuario.getIdByEmail(parametros.get(1));
        int idUsuarioParametro = Integer.parseInt(parametros.get(0)); //id
        
        // verifica que el email este disponible
        if (idUsuario == -1 || (idUsuario == idUsuarioParametro)) {
            dUsuario.modificar(idUsuarioParametro, parametros.get(1), 
                parametros.get(2), parametros.get(3), parametros.get(4), parametros.get(5));
            
            dPersonal.modificar(idUsuarioParametro, parametros.get(6));
        }
        
        dUsuario.desconectar();
        dPersonal.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dPersonal.eliminar(Integer.parseInt(parametros.get(0)));
        dUsuario.eliminar(Integer.parseInt(parametros.get(0)));
        
        dUsuario.desconectar();
        dPersonal.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> usuarios = (ArrayList<String[]>) dPersonal.listar();
        dPersonal.desconectar();
        return usuarios;
    }
}
