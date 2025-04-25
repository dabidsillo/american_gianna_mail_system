/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import coneccion.comunicacion.MailVerificationThread;
import coneccion.comunicacion.SendEmailThread;
import coneccion.interfaces.IEmailEventListener;
import coneccion.utils.Email;
import interpreter.MainPrueba;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.events.TokenEvent;
import java.util.List;
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
import interpreter.analex.Interpreter;
import interpreter.analex.utils.Token;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negociodato.dato.DCliente;
import negociodato.dato.DComando;
import negociodato.dato.DDetalleVenta;
import negociodato.dato.DCategoria;
import negociodato.dato.DInventario;
import negociodato.dato.DNotaIngreso;
import negociodato.dato.DPago;
import negociodato.dato.DPersonal;
import negociodato.dato.DProducto;
import negociodato.dato.DPromocion;
import negociodato.dato.DProveedor;
import negociodato.dato.DServicio;
import negociodato.dato.DVenta;
import negociodato.negocio.NComando;

/**
 *
 * @author DAVID, ELMER
 */
public class Aplication implements IEmailEventListener, ITokenEventListener {

    private static final int CONSTRAINTS_ERROR = -2;
    private static final int NUMBER_FORMAT_ERROR = -3;
    private static final int INDEX_OUT_OF_BOUND_ERROR = -4;
    private static final int PARSE_ERROR = -5;
    private static final int AUTHORIZATION_ERROR = -6;

    private MailVerificationThread mailVerificationThread;

    private NCategoria nCategoria;
    private NPromocion nPromocion;
    private NProducto nProducto;
    private NCliente nCliente;
    private NPersonal nPersonal;
    private NProveedor nProveedor;
    private NServicio nServicio;
    private NVenta nVenta;
    private NDetalleVenta nDetalleVenta;
    private NPago nPago;
    private NInventario nInventario;
    private NNotaIngreso nNotaIngreso;
    private NComando nComando;

    public Aplication() {
        mailVerificationThread = new MailVerificationThread();
        mailVerificationThread.setEmailEventListener(Aplication.this);

        nCategoria = new NCategoria();
        nPromocion = new NPromocion();
        nProducto = new NProducto();
        nCliente = new NCliente();
        nPersonal = new NPersonal();
        nProveedor = new NProveedor();
        nServicio = new NServicio();
        nVenta = new NVenta();
        nDetalleVenta = new NDetalleVenta();
        nPago = new NPago();
        nInventario = new NInventario();
        nNotaIngreso = new NNotaIngreso();
        nComando = new NComando();
    }

    public void start() {
        Thread thread = new Thread(mailVerificationThread);
        thread.setName("Mail Verfication Thread");
        thread.start();
    }

    @Override
    public void onReceiveEmailEvent(List<Email> emails) {
        for (Email email : emails) {
            String subject = email.getSubject() + " ";
            Interpreter interpreter = new Interpreter(subject, email.getFrom());
            interpreter.setListener(Aplication.this);
            Thread thread = new Thread(interpreter);
            thread.setName("Interpreter Thread");
            thread.start();
        }
    }

    @Override
    public void help(TokenEvent event) {
        System.out.println("HELP");
        try {
            tableNotifySuccess(event.getSender(), "Lista de Comandos", DComando.HEADERS, nComando.listar());
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void cliente(TokenEvent event) {
        System.out.println("USUARIO (CLIENTE)");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nCliente.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Cliente Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nCliente.modificar(event.getParams());
                    System.out.println("Cliente modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Cliente Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nCliente.eliminar(event.getParams());
                    System.out.println("Cliente eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Cliente Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Clientes", DCliente.HEADERS, nCliente.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void personal(TokenEvent event) {
        System.out.println("USUARIO (Personal)");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nPersonal.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Personal Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nPersonal.modificar(event.getParams());
                    System.out.println("Personal modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Personal Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nPersonal.eliminar(event.getParams());
                    System.out.println("Personal eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Personal Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Personal", DPersonal.HEADERS, nPersonal.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void proveedor(TokenEvent event) {
        System.out.println("USUARIO (Proveedor)");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nProveedor.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Proveedor Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nProveedor.modificar(event.getParams());
                    System.out.println("Proveedor modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Proveedor Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nProveedor.eliminar(event.getParams());
                    System.out.println("Proveedor eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Proveedor Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Proveedores", DProveedor.HEADERS, nProveedor.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void categoria(TokenEvent event) {
        System.out.println("CATEGORIA");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nCategoria.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Categoria Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nCategoria.modificar(event.getParams());
                    System.out.println("Categoria modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Categoria Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nCategoria.eliminar(event.getParams());
                    System.out.println("Categoria eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Categoria Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Categorias", DCategoria.HEADERS, nCategoria.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void promocion(TokenEvent event) {
        System.out.println("PROMOCION");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nPromocion.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Promocion Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nPromocion.modificar(event.getParams());
                    System.out.println("Promocion modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Promocion Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nPromocion.eliminar(event.getParams());
                    System.out.println("Promocion eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Promocion Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Promociones", DPromocion.HEADERS, nPromocion.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void producto(TokenEvent event) {
        System.out.println("PRODUCTO");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nProducto.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Producto Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nProducto.modificar(event.getParams());
                    System.out.println("Producto modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Producto Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nProducto.eliminar(event.getParams());
                    System.out.println("Producto eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Producto Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Productos", DProducto.HEADERS, nProducto.listar());
                    break;
                case Token.GRAFICA:
                    tableGraficaSuccess3(event.getSender(), "Grafica De Productos Mas Vendidos", nProducto.graficaDeProductoMasVendido());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void servicio(TokenEvent event) {
        System.out.println("SERVICIO");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nServicio.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Servicio Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nServicio.modificar(event.getParams());
                    System.out.println("Servicio modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Servicio Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nServicio.eliminar(event.getParams());
                    System.out.println("Servicio eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Servicio Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Servicios", DServicio.HEADERS, nServicio.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void venta(TokenEvent event) {
        System.out.println("VENTA");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nVenta.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Venta Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nVenta.modificar(event.getParams());
                    System.out.println("Venta modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Venta Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nVenta.eliminar(event.getParams());
                    System.out.println("Venta eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Venta Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Ventas", DVenta.HEADERS, nVenta.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pago(TokenEvent event) {
        System.out.println("PAGO");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nPago.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Pago Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nPago.modificar(event.getParams());
                    System.out.println("Pago modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Pago Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nPago.eliminar(event.getParams());
                    System.out.println("Pago eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Pago Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Pagos", DPago.HEADERS, nPago.listar());
                    break;
                case Token.GRAFICA:
                    tableGraficaSuccess(event.getSender(), "Grafica De Tipos De Pago Mas Utilizados", nPago.graficaTiposDePagoMasUtilizados());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void detalle_venta(TokenEvent event) {
        System.out.println("DETALLE VENTA");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nDetalleVenta.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Detalle Venta Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nDetalleVenta.modificar(event.getParams());
                    System.out.println("Detalle Venta modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Detalle Venta Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nDetalleVenta.eliminar(event.getParams());
                    System.out.println("Detalle Venta eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Detalle Venta Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Detalle Ventas", DDetalleVenta.HEADERS, nDetalleVenta.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inventario(TokenEvent event) {
        System.out.println("INVENTARIO");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nInventario.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Inventario Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nInventario.modificar(event.getParams());
                    System.out.println("Inventario modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Inventario Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nInventario.eliminar(event.getParams());
                    System.out.println("Inventario eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Inventario Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Inventarios", DInventario.HEADERS, nInventario.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            System.out.println("error Parse Exception");
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void nota_ingreso(TokenEvent event) {
        System.out.println("NOTA INGRESO");
        try {
            switch (event.getAction()) {
                case Token.AGREGAR:
                    nNotaIngreso.guardar(event.getParams());
                    System.out.println("Guardado con exito");
                    simpleNotifySuccess(event.getSender(), "Nota Ingreso Guardado Correctamente");
                    break;
                case Token.MODIFICAR:
                    nNotaIngreso.modificar(event.getParams());
                    System.out.println("Nota Ingreso modificado con exito");
                    simpleNotifySuccess(event.getSender(), "Nota Ingreso Modificado Correctamente");
                    break;
                case Token.ELIMINAR:
                    nNotaIngreso.eliminar(event.getParams());
                    System.out.println("Nota Ingreso eliminado con exito");
                    simpleNotifySuccess(event.getSender(), "Nota Ingreso Eliminado Correctamente");
                    break;
                case Token.LISTAR:
                    tableNotifySuccess(event.getSender(), "Lista de Notas Ingresos", DNotaIngreso.HEADERS, nNotaIngreso.listar());
                    break;

            }
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void error(TokenEvent event) {
        this.handleError(event.getAction(), event.getSender(), event.getParams());
    }

    private void handleError(int type, String email, List<String> args) {
        Email emailObject = null;

        switch (type) {
            case Token.ERROR_CHARACTER:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Caracter desconocido",
                    "No se pudo ejecutar el comando [" + args.get(0) + "] debido a: ",
                    "El caracter \"" + args.get(1) + "\" es desconocido."
                }));
                break;
            case Token.ERROR_COMMAND:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Comando desconocido",
                    "No se pudo ejecutar el comando [" + args.get(0) + "] debido a: ",
                    "No se reconoce la palabra \"" + args.get(1) + "\" como un comando vÃ¡lido"
                }));
                break;
            case CONSTRAINTS_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error al interactuar con la base de datos",
                    "Referencia a informaciÃ³n inexistente"
                }));
                break;
            case NUMBER_FORMAT_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error en el tipo de parÃ¡metro",
                    "El tipo de uno de los parÃ¡metros es incorrecto"
                }));
                break;
            case INDEX_OUT_OF_BOUND_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Cantidad de parÃ¡metros incorrecta",
                    "La cantidad de parÃ¡metros para realizar la acciÃ³n es incorrecta"
                }));
                break;
            case PARSE_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error al procesar la fecha",
                    "La fecha introducida posee un formato incorrecto"
                }));
                break;
            case AUTHORIZATION_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Acceso denegado",
                    "Usted no posee los permisos necesarios para realizar la acciÃ³n solicitada"
                }));
                break;
        }

        this.sendEmail(emailObject);
    }

    private void simpleNotifySuccess(String email, String message) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateText(new String[]{
            "Peticion realizada correctamente",
            message
        }));
        sendEmail(emailObject);
    }

    private void simpleNotify(String email, String title, String topic, String message) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateText(new String[]{
            title, topic, message
        }));
        sendEmail(emailObject);
    }

    private void tableNotifySuccess(String email, String title, String[] headers, ArrayList<String[]> data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateTable(title, headers, data));
        sendEmail(emailObject);
    }

    private void tableNotifySuccess2(String email, String title, String[] headers, ArrayList<String[]> data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateTable(title, headers, data));
        sendEmail(emailObject);
    }

    private void tableGraficaSuccess(String email, String title, ArrayList<String[]> data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateGrafica(title, data));
        sendEmail(emailObject);
    }

    private void tableGraficaSuccess2(String email, String title, ArrayList<String[]> data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateGrafica2(title, data));
        sendEmail(emailObject);
    }
    
    private void tableGraficaSuccess3(String email, String title, ArrayList<String[]> data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateGrafica3(title, data));
        sendEmail(emailObject);
    }

    private void simpleTableNotifySuccess(String email, String title, String[] headers, String[] data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateTableForSimpleData(title, headers, data));
        sendEmail(emailObject);
    }

    private void sendEmail(Email email) {
        SendEmailThread sendEmail = new SendEmailThread(email);
        Thread thread = new Thread(sendEmail);
        thread.setName("Send email Thread");
        thread.start();
    }

    
}
