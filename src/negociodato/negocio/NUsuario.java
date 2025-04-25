
package negociodato.negocio;

import negociodato.dato.DUsuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class NUsuario {
    
    private final DUsuario dUsuario;
    
    public NUsuario() {
        this.dUsuario = new DUsuario();
    }
    
     public void guardar(List<String> parametros) throws SQLException {
        // verificar que el email y telefono sea unico 
        int idUsuario = dUsuario.getIdByEmail(parametros.get(0));
        
         if (idUsuario != -1) {
            dUsuario.guardar(parametros.get(0), parametros.get(1), parametros.get(2),
                parametros.get(3), parametros.get(4));
         }

        dUsuario.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException {
        // verificar que el email y telefono enviado sean unicos
        int idUsuario = dUsuario.getIdByEmail(parametros.get(0));
        int idUsuarioParametro = Integer.parseInt(parametros.get(0)); //id
        
        if (idUsuario != -1 && idUsuario != idUsuarioParametro) {
            dUsuario.modificar(idUsuarioParametro, parametros.get(1), 
                parametros.get(2), parametros.get(3), parametros.get(4), parametros.get(5));
        }
        
        dUsuario.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dUsuario.eliminar(Integer.parseInt(parametros.get(0)));
        dUsuario.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> usuarios = (ArrayList<String[]>) dUsuario.listar();
        dUsuario.desconectar();
        return usuarios;
    }
    
}
