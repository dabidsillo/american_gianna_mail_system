
package negociodato;


import negociodato.negocio.NCategoria;
import negociodato.negocio.NProducto;
import negociodato.negocio.NPromocion;
import negociodato.negocio.NUsuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Negocio {
    public static void main(String[] args) {
//        genero();
//        promocion();
        producto();
//        usuario();
    }
    
    public static void usuario() {
                List<String> usuario = new ArrayList<>();
//        usuario.add("3");
        usuario.add("juan@gmail.com");
        usuario.add("12345678");
        usuario.add("Juan Perez edit");
        usuario.add("76042143");
        usuario.add("Av. Bush editado");
        
        try {
            NUsuario nUsuario = new NUsuario();
            nUsuario.guardar(usuario);       
//          nUsuario.modificar(usuario);       
//          nUsuario.eliminar(usuario);
            ArrayList<String[]> listaUsuarios = nUsuario.listar();
            System.out.println(Arrays.toString(listaUsuarios.get(0)));
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void categoria() {
        List<String> categoria = new ArrayList<>();
//        genero.add("1");
        categoria.add("Camisas");
        
        try {
            NCategoria nCategoria = new NCategoria();
            nCategoria.guardar(categoria);
            System.out.println("Categoria agregado exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void promocion() {
        List<String> promocion = new ArrayList<>();
//        promocion.add("1");
        promocion.add("Fiesta de Navidad");
        promocion.add("30");
        try {
            NPromocion nPromocion = new NPromocion();
            nPromocion.guardar(promocion);
            System.out.println("Promocion agregado exitosamente");
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void producto() {
        List<String> producto = new ArrayList<>();
//        usuario.add("3");
        producto.add("Camisa Deportiva 2");
        producto.add("Camisa Deoportiva para usao frecuente");
        producto.add("Camisa Deportiva");
        producto.add("url de la imagen");
        producto.add("20");
        producto.add("30");
        producto.add("2");
//        producto.add("3");
        
        
        try {
            NProducto nProducto = new NProducto();
            nProducto.guardar(producto);       
//          nUsuario.modificar(usuario);       
//          nUsuario.eliminar(usuario);

            ArrayList<String[]> listaProductos = nProducto.listar();
            
            for (int i = 0; i < listaProductos.size(); i++) {
                String[] productoActual = listaProductos.get(i);
                System.out.println(Arrays.toString(productoActual));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
