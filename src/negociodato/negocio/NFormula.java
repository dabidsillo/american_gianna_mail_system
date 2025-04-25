package negociodato.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DFormula;

public class NFormula {

    private final DFormula dFormula;

    public NFormula() {
        dFormula = new DFormula();
    }

    public void guardar(List<String> parametros) throws SQLException {
        String nombre = parametros.get(0);
        String descripcion = parametros.get(1);
        dFormula.guardar(nombre, descripcion);
        dFormula.desconectar();
    }

    public void modificar(List<String> parametros) throws SQLException {
        int id = Integer.parseInt(parametros.get(0));
        String nombre = parametros.get(1);
        String descripcion = parametros.get(2);
        dFormula.modificar(id, nombre, descripcion);
        dFormula.desconectar();
    }

    public void eliminar(List<String> parametros) throws SQLException {
        int id = Integer.parseInt(parametros.get(0));
        dFormula.eliminar(id);
        dFormula.desconectar();
    }

    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> formulas = (ArrayList<String[]>) dFormula.listar();
        dFormula.desconectar();
        return formulas;
    }
}
