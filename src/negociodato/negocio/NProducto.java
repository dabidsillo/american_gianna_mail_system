/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negociodato.negocio;

import negociodato.dato.DProducto;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DCategoria;
import negociodato.dato.DPromocion;

/**
 *
 * @author DAVID ELMER 
 */
public class NProducto {
    
    private final DProducto dProducto;
    private final DCategoria dCategoria;
        private final DPromocion dPromocion;
    
    public NProducto() {
        this.dProducto = new DProducto();
        this.dCategoria = new DCategoria();
        this.dPromocion = new DPromocion();
    }
    
    public void guardar(List<String> parametros) throws SQLException, ParseException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        if (parametros.size() < 8) {
            System.out.println("size " + parametros.size());
            parametros.add("0");
        }
                System.out.println(parametros.get(6));

        int id_categoria = dCategoria.getIdByNombre(parametros.get(6));
        int id_promocion = "0".equals(parametros.get(7)) ? 0 : dPromocion.getIdByNombre(parametros.get(7)) ;

        if (id_categoria != -1 && (id_promocion != -1 || id_promocion == 0)) {
            dProducto.guardar(parametros.get(0), parametros.get(1), parametros.get(2),
                parametros.get(3), Integer.parseInt(parametros.get(4)), Integer.parseInt(parametros.get(5)), 
                id_categoria, id_promocion);
        }

        dProducto.desconectar();
        dCategoria.desconectar();
        dPromocion.desconectar();
    }
    
    public void modificar(List<String> parametros) throws SQLException, ParseException{
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }
        
        if (parametros.size() < 9) {
            System.out.println("size " + parametros.size());
            parametros.add("0");
        }

        int id_categoria = dCategoria.getIdByNombre(parametros.get(7));
        int id_promocion = "0".equals(parametros.get(8)) ? 0 : dPromocion.getIdByNombre(parametros.get(8)) ;
        
        if (id_categoria != -1 && (id_promocion != -1 || id_promocion == 0)) {

            dProducto.modificar(Integer.parseInt(parametros.get(0)),parametros.get(1), parametros.get(2),
                parametros.get(3), parametros.get(4), Integer.parseInt(parametros.get(5)),
                Integer.parseInt(parametros.get(6)), id_categoria, id_promocion);   
        }

        dProducto.desconectar();
        dCategoria.desconectar();
        dPromocion.desconectar();
    }
    
    public void eliminar(List<String> parametros) throws SQLException {
        dProducto.eliminar(Integer.parseInt(parametros.get(0)));
        dProducto.desconectar();
    }
    
    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> productos = (ArrayList<String[]>) dProducto.listar();
        dProducto.desconectar();
        return productos;
    }
    
    public ArrayList<String[]> graficaDeProductoMasVendido() throws SQLException {
        ArrayList<String[]> productos = (ArrayList<String[]>) dProducto.graficaDeProductoMasVendido();
        dProducto.desconectar();
        return productos;
    }
    
}
