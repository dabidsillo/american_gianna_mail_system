
package interpreter.analex.interfaces;

import interpreter.events.TokenEvent;


//AJUSTAR DE ACUERDO A NUESTRO CU
public interface ITokenEventListener {
    
    void help(TokenEvent event);   
    void cliente(TokenEvent event);     
    void personal(TokenEvent event);
    void proveedor(TokenEvent event);
    void promocion(TokenEvent event);
    void categoria(TokenEvent event);
    void producto(TokenEvent event);
    void servicio(TokenEvent event);
    void venta(TokenEvent event);
    void pago(TokenEvent event);
    void detalle_venta(TokenEvent event);
    void inventario(TokenEvent event);
    void nota_ingreso(TokenEvent event);
    
    void error(TokenEvent event);
    //agregar mas casos de uso
}
