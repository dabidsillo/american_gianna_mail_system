
package interpreter.analex;

import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.analex.models.TokenCommand;
import interpreter.analex.utils.Token;
import interpreter.events.TokenEvent;


public class Interpreter implements Runnable{
    private ITokenEventListener listener;
    private Analex analex;
    
    private String command;
    private String sender;
    
    public Interpreter(String command, String sender) {
        this.command = command;
        this.sender = sender;        
    }

    public ITokenEventListener getListener() {
        return listener;
    }

    public void setListener(ITokenEventListener listener) {
        this.listener = listener;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    private void filterEvent(TokenCommand token_command){
        TokenEvent token_event = new TokenEvent(this, sender);        
        token_event.setAction(token_command.getAction());
        
        int count_params = token_command.countParams();
        for(int i = 0; i < count_params; i++){
            int pos = token_command.getParams(i);
            token_event.addParams(analex.getParam(pos));
        }
        
        //ajustar de acuerdo a su casos de uso
        switch(token_command.getName()){
            case Token.HELP:
                listener.help(token_event);
                break;
            case Token.CLIENTE:
                listener.cliente(token_event);
                break;
            case Token.PERSONAL:
                listener.personal(token_event);
                break;
            case Token.PROVEEDOR:
                listener.proveedor(token_event);
                break;
            case Token.PROMOCION:
                listener.promocion(token_event);
                break;
            case Token.CATEGORIA:
                listener.categoria(token_event);
                break;
            case Token.PRODUCTO:
                listener.producto(token_event);
                break;
            case Token.SERVICIO:
                listener.servicio(token_event);
                break;
            case Token.VENTA:
                listener.venta(token_event);
                break;    
            case Token.PAGO:
                listener.pago(token_event);
                break;
            case Token.DETALLEVENTA:
                listener.detalle_venta(token_event);
                break;
            case Token.INVENTARIO:
                listener.inventario(token_event);
                break;
            case Token.NOTAINGRESO:
                listener.nota_ingreso(token_event);
                break;
        }
        
    }
    
    private void tokenError(Token token, String error){
        TokenEvent token_event = new TokenEvent(this, sender);
        token_event.setAction(token.getAttribute());
        token_event.addParams(command);
        token_event.addParams(error);
        listener.error(token_event);
    }
    
    @Override
    public void run() {
        analex = new Analex(command);
        TokenCommand token_command = new TokenCommand();
        Token token;
        
        //while(analex.Preanalisis() != null) {
            //token = analex.Preanalisis();
            //if (token.getName() == Token.END && token.getName() == Token.ERROR) {
                //break;
            //}
        //}
        
        while((token = analex.Preanalisis()).getName() != Token.END && token.getName() != Token.ERROR){
            if(token.getName() == Token.CU){
                token_command.setName(token.getAttribute());// id del CU
            } else if(token.getName() == Token.ACTION){
                token_command.setAction(token.getAttribute());// id de la accion
            } else if(token.getName() == Token.PARAMS){
                token_command.addParams(token.getAttribute());// la posicion del parametro en el tsp
            }
            analex.next();
        }
        
        if(token.getName() == Token.END){
            filterEvent(token_command);// se analizo el comando con exito
        } else if(token.getName() == Token.ERROR){
            tokenError(token, analex.lexeme()); // se produjo un error en el analisis
        }
        
    }
}
