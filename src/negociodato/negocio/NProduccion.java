package negociodato.negocio;

import negociodato.dato.DProduccion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NProduccion {

    private final DProduccion dProduccion;

    public NProduccion() {
        this.dProduccion = new DProduccion();
    }

    public void guardar(List<String> parametros) throws SQLException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }

        dProduccion.guardar(parametros.get(0), parametros.get(1));
        dProduccion.desconectar();
    }

    public void modificar(List<String> parametros) throws SQLException {
        if (parametros.isEmpty()) {
            throw new SQLException("Parametros vacios!");
        }

        dProduccion.modificar(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2));
        dProduccion.desconectar();
    }

    public void eliminar(List<String> parametros) throws SQLException {
        dProduccion.eliminar(Integer.parseInt(parametros.get(0)));
        dProduccion.desconectar();
    }

    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> producciones = (ArrayList<String[]>) dProduccion.listar();
        dProduccion.desconectar();
        return producciones;
    }
}
