package coneccion;

import coneccion.comunicacion.MailVerificationThread;
import coneccion.comunicacion.SendEmailThread;
import coneccion.interfaces.IEmailEventListener;
import coneccion.utils.Email;
import interpreter.MainPrueba;
import interpreter.analex.Interpreter;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.analex.utils.Token;
import interpreter.events.TokenEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negociodato.negocio.NCategoria;
import negociodato.negocio.NProducto;
import negociodato.negocio.NPromocion;

/**
 *
 * @author DAVID, ELMER
 */
public class Coneccion {

    public static void main(String[] args) {
        System.out.println("hello world");
        
        MailVerificationThread mail = new MailVerificationThread();
        mail.setEmailEventListener(new IEmailEventListener() {
            @Override
            public void onReceiveEmailEvent(List<Email> emails) {
                for (Email email : emails) {
                    System.out.println(email);
                    interpreter(email);
                }
            }
        });
        
        Thread thread = new Thread(mail);
        thread.setName("Mail Verification Thread");
        thread.start();
        
    }
    
    
    public static void interpreter(Email email ){
        
        
        NCategoria nCategoria = new NCategoria();
        NPromocion nPromocion = new NPromocion();
        NProducto nProducto = new NProducto();
        
        String sender = email.getFrom() + " ";
        Interpreter interpreter = new Interpreter(email.getSubject(), sender);  
        
        interpreter.setListener(new ITokenEventListener() {


            @Override
            public void cliente(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


            @Override
            public void producto(TokenEvent event) {
                System.out.println("CU: PRODUCTO");
                System.out.println(event);
                try {
                    if(event.getAction() == Token.AGREGAR) {
                        nProducto.guardar(event.getParams());    
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if(event.getAction() == Token.MODIFICAR) {
                        nProducto.modificar(event.getParams());  
                        System.out.println("OK");
                    } else if(event.getAction() == Token.ELIMINAR) {
                        nProducto.eliminar(event.getParams());  
                        System.out.println("OK");
                    } else if(event.getAction() == Token.LISTAR) {
                        ArrayList<String[]> lista = nProducto.listar();
                        
                        String s = "";
                        for(int i = 0; i < lista.size(); i++) {
                            s = s + "["+i+"] : ";
                            for(int j = 0; j <lista.get(i).length; j++) {
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
                    System.out.println("Mensaje: "+ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            
            @Override
            public void categoria(TokenEvent event) {
                System.out.println("entro aqui");
                System.out.println("CU: CATEGORIA");
                System.out.println(event);
                try {
                    if(event.getAction() == Token.AGREGAR) {
                        nCategoria.guardar(event.getParams());    
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if(event.getAction() == Token.MODIFICAR) {
                        nCategoria.guardar(event.getParams());  
                        System.out.println("OK");
                    } else if(event.getAction() == Token.ELIMINAR) {
                        nCategoria.guardar(event.getParams());  
                        System.out.println("OK");
                    } else if(event.getAction() == Token.LISTAR) {
                        ArrayList<String[]> lista = nCategoria.listar();
                        
                        String s = "";
                        for(int i = 0; i < lista.size(); i++) {
                            s = s + "["+i+"] : ";
                            for(int j = 0; j <lista.get(i).length; j++) {
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
                    System.out.println("Mensaje: "+ex.getSQLState());
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
            public void personal(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void proveedor(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void promocion(TokenEvent event) {
                System.out.println("CU: PROMOCION");
                System.out.println(event);
                try {
                    if(event.getAction() == Token.AGREGAR) {
                        nPromocion.guardar(event.getParams());    
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if(event.getAction() == Token.MODIFICAR) {
                        nPromocion.modificar(event.getParams());  
                        System.out.println("OK");
                    } else if(event.getAction() == Token.ELIMINAR) {
                        nPromocion.eliminar(event.getParams());  
                        System.out.println("OK");
                    } else if(event.getAction() == Token.LISTAR) {
                        ArrayList<String[]> lista = nPromocion.listar();
                        
                        String s = "";
                        for(int i = 0; i < lista.size(); i++) {
                            s = s + "["+i+"] : ";
                            for(int j = 0; j <lista.get(i).length; j++) {
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
                    System.out.println("Mensaje: "+ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void servicio(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void venta(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void pago(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void detalle_venta(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void inventario(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void nota_ingreso(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            
            
        });
        
        
        Thread thread = new Thread(interpreter);
        thread.setName("Interpreter Thread");
        thread.start();
    }
    
}
