package interpreter;

import interpreter.analex.Interpreter;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.events.TokenEvent;
import interpreter.analex.utils.Token;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negociodato.negocio.NCliente;
import negociodato.negocio.NDetalleVenta;
import negociodato.negocio.NCategoria;
import negociodato.negocio.NInventario;
import negociodato.negocio.NNotaIngreso;
import negociodato.negocio.NPago;
import negociodato.negocio.NPersonal;
import negociodato.negocio.NProducto;
import negociodato.negocio.NPromocion;
import negociodato.negocio.NProveedor;
import negociodato.negocio.NServicio;
import negociodato.negocio.NVenta;

/**
 *
 * @author DAVID, ELMER
 */
public class MainPrueba {

    public static void main(String[] args) {

        // genero agregar [Horror]
        // promocion agregar [Navidad; 30]
        // producto agregar [Clean Code; Libro de codigo limpio; Robert C. Martin; url de la imagen; 50; 80; Horror; Navidad]
        // producto modificar [1; Clean Code; Libro de editado; Robert C Martin; url de la imagen; 50; 80; Horror; Navidad]
        // proveedor agregar [pepe@gmail.com; 1234; Pepe Perez; 7711222; Av Bush; 13524; Amazon]
        // personal agregar [pedro@gmail.com; 1234; Pedro Perez; 63112300; Av Bush; contador]
        // cliente agregar [juan@gmail.com; 1234; Juan Perez; 62124027; Av Bush; 13241457]
        // servicio agregar [Delivery; 15]
        // servicio modificar [1; Delivery; 20]
        // venta agregar [70; juan@gmail.com; Delivery] 
        // detalleventa agregar [1; 50; 1; 1]
        // pago agregar [tigo money; 2023-11-25; 13:30; venta de producto; 1]
        // inventario agregar [libro de programacion; 1; 1]
        // notaingreso agregar [10; 30; 300; 1; 1; 2]
        String comando = "producto grafica ";
        String correo = "ramirez.ricky2021@gmail.com";
        
        
        NCategoria nCategoria = new NCategoria();
        NPromocion nPromocion = new NPromocion();
        NProducto nProducto = new NProducto();

        NCliente nCliente = new NCliente();
        NPersonal nPersonal = new NPersonal();
        NProveedor nProveedor = new NProveedor();
        NServicio nServicio = new NServicio();
        NVenta nVenta = new NVenta();
        NDetalleVenta nDetalleVenta = new NDetalleVenta();
        NPago nPago = new NPago();
        NInventario nInventario = new NInventario();
        NNotaIngreso nNotaIngreso = new NNotaIngreso();

        Interpreter interpreter = new Interpreter(comando, correo);

        interpreter.setListener(new ITokenEventListener() {

            @Override
            public void cliente(TokenEvent event) {
                System.out.println("CU: USUARIO (CLIENTE)");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nCliente.guardar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.MODIFICAR:
                            nCliente.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nCliente.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nCliente.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void personal(TokenEvent event) {
                System.out.println("CU: USUARIO (PERSONAL)");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nPersonal.guardar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.MODIFICAR:
                            nPersonal.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nPersonal.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nPersonal.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("ocurrio un error al guardar");
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void proveedor(TokenEvent event) {
                System.out.println("CU: USUARIO (PROVEEDOR)");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nProveedor.guardar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.MODIFICAR:
                            nProveedor.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nProveedor.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nProveedor.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("ocurrio un error al guardar");
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void producto(TokenEvent event) {
                System.out.println("CU: PRODUCTO");
                System.out.println(event);
                try {
                    if (event.getAction() == Token.AGREGAR) {
                        nProducto.guardar(event.getParams());
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if (event.getAction() == Token.MODIFICAR) {
                        nProducto.modificar(event.getParams());
                        System.out.println("OK");
                    } else if (event.getAction() == Token.ELIMINAR) {
                        nProducto.eliminar(event.getParams());
                        System.out.println("OK");
                    } else if (event.getAction() == Token.LISTAR) {
                        ArrayList<String[]> lista = nProducto.listar();

                        String s = "";
                        for (int i = 0; i < lista.size(); i++) {
                            s = s + "[" + i + "] : ";
                            for (int j = 0; j < lista.get(i).length; j++) {
                                s = s + lista.get(i)[j] + " | ";
                            }
                            s = s + "\n";
                        }
                        System.out.println(s);
                    } else if (event.getAction() == Token.GRAFICA) {
                        ArrayList<String[]> lista = nProducto.graficaDeProductoMasVendido();

                        String s = "";
                        for (int i = 0; i < lista.size(); i++) {
                            s = s + "[" + i + "] : ";
                            for (int j = 0; j < lista.get(i).length; j++) {
                                s = s + lista.get(i)[j] + " | ";
                            }
                            s = s + "\n";
                        }
                        System.out.println(s);
                    } else {
                        System.out.println("La accion no es valida para el caso de uso");
                        //enviar al correo una notificacion
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void categoria(TokenEvent event) {
                System.out.println("CU: CATEGORIA");
                System.out.println(event);
                try {
                    if (event.getAction() == Token.AGREGAR) {
                        nCategoria.guardar(event.getParams());
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if (event.getAction() == Token.MODIFICAR) {
                        nCategoria.modificar(event.getParams());
                        System.out.println("OK");
                    } else if (event.getAction() == Token.ELIMINAR) {
                        nCategoria.eliminar(event.getParams());
                        System.out.println("OK");
                    } else if (event.getAction() == Token.LISTAR) {
                        ArrayList<String[]> lista = nCategoria.listar();

                        String s = "";
                        for (int i = 0; i < lista.size(); i++) {
                            s = s + "[" + i + "] : ";
                            for (int j = 0; j < lista.get(i).length; j++) {
                                s = s + lista.get(i)[j] + " | ";
                            }
                            s = s + "\n";
                        }
                        System.out.println(s);
                    } else {
                        System.out.println("La accion no es valida para el caso de uso");
                        //enviar al correo una notificacion
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void error(TokenEvent event) {
                System.out.println("ocurrio un error");
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void help(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void promocion(TokenEvent event) {
                System.out.println("CU: PROMOCION");
                System.out.println(event);
                try {
                    if (event.getAction() == Token.AGREGAR) {
                        nPromocion.guardar(event.getParams());
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if (event.getAction() == Token.MODIFICAR) {
                        nPromocion.modificar(event.getParams());
                        System.out.println("OK");
                    } else if (event.getAction() == Token.ELIMINAR) {
                        nPromocion.eliminar(event.getParams());
                        System.out.println("OK");
                    } else if (event.getAction() == Token.LISTAR) {
                        ArrayList<String[]> lista = nPromocion.listar();

                        String s = "";
                        for (int i = 0; i < lista.size(); i++) {
                            s = s + "[" + i + "] : ";
                            for (int j = 0; j < lista.get(i).length; j++) {
                                s = s + lista.get(i)[j] + " | ";
                            }
                            s = s + "\n";
                        }
                        System.out.println(s);
                    } else {
                        System.out.println("La accion no es valida para el caso de uso");
                        //enviar al correo una notificacion
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void servicio(TokenEvent event) {
                System.out.println("CU: SERVICIO");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nServicio.guardar(event.getParams());
                            System.out.println("OK");
                            //notificar al usuario que se ejecuto su comando
                            break;
                        case Token.MODIFICAR:
                            nServicio.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nServicio.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nServicio.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void venta(TokenEvent event) {
                System.out.println("CU: VENTA");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nVenta.guardar(event.getParams());
                            System.out.println("OK");
                            //notificar al usuario que se ejecuto su comando
                            break;
                        case Token.MODIFICAR:
                            nVenta.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nVenta.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nVenta.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void pago(TokenEvent event) {
                System.out.println("CU: DETALLE VENTA");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nPago.guardar(event.getParams());
                            System.out.println("OK");
                            //notificar al usuario que se ejecuto su comando
                            break;
                        case Token.MODIFICAR:
                            nPago.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nPago.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nPago.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void detalle_venta(TokenEvent event) {
                System.out.println("CU: DETALLE VENTA");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nDetalleVenta.guardar(event.getParams());
                            System.out.println("OK");
                            //notificar al usuario que se ejecuto su comando
                            break;
                        case Token.MODIFICAR:
                            nDetalleVenta.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nDetalleVenta.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nDetalleVenta.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void inventario(TokenEvent event) {
                System.out.println("CU: INVENTARIO");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nInventario.guardar(event.getParams());
                            System.out.println("OK");
                            //notificar al usuario que se ejecuto su comando
                            break;
                        case Token.MODIFICAR:
                            nInventario.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nInventario.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nInventario.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void nota_ingreso(TokenEvent event) {
                System.out.println("CU: NOTA INGRESO");
                System.out.println(event);
                try {
                    switch (event.getAction()) {
                        case Token.AGREGAR:
                            nNotaIngreso.guardar(event.getParams());
                            System.out.println("OK");
                            //notificar al usuario que se ejecuto su comando
                            break;
                        case Token.MODIFICAR:
                            nNotaIngreso.modificar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.ELIMINAR:
                            nNotaIngreso.eliminar(event.getParams());
                            System.out.println("OK");
                            break;
                        case Token.LISTAR:
                            ArrayList<String[]> lista = nNotaIngreso.listar();
                            String s = "";
                            for (int i = 0; i < lista.size(); i++) {
                                s = s + "[" + i + "] : ";
                                for (int j = 0; j < lista.get(i).length; j++) {
                                    s = s + lista.get(i)[j] + " | ";
                                }
                                s = s + "\n";
                            }
                            System.out.println(s);
                            break;
                        default:
                            System.out.println("La accion no es valida para el caso de uso");
                            //enviar al correo una notificacion
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

        });

        Thread thread = new Thread(interpreter);
        thread.setName("Interpreter Thread");
        thread.start();
    }
}
