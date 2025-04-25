package negociodato.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negociodato.dato.DInsumo;

public class NInsumo {
    private final DInsumo dInsumo;

    public NInsumo() {
        this.dInsumo = new DInsumo();
    }

    public void guardar(List<String> parametros) throws SQLException {
        String nombre = parametros.get(0);
        int cantidad = Integer.parseInt(parametros.get(1));
        double precio = Double.parseDouble(parametros.get(2));
        dInsumo.guardar(nombre, cantidad, precio);
        dInsumo.desconectar();
    }

    public void modificar(List<String> parametros) throws SQLException {
        int id = Integer.parseInt(parametros.get(0));
        String nombre = parametros.get(1);
        int cantidad = Integer.parseInt(parametros.get(2));
        double precio = Double.parseDouble(parametros.get(3));
        dInsumo.modificar(id, nombre, cantidad, precio);
        dInsumo.desconectar();
    }

    public void eliminar(List<String> parametros) throws SQLException {
        int id = Integer.parseInt(parametros.get(0));
        dInsumo.eliminar(id);
        dInsumo.desconectar();
    }

    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> insumos = (ArrayList<String[]>) dInsumo.listar();
        dInsumo.desconectar();
        return insumos;
    }
}
