package dados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroCliente {
    private List<Cliente> lista;

    public RegistroCliente (){
        lista = new ArrayList<>();
    }

    public boolean cadastraCliente(Cliente cliente) {
        if (lista.isEmpty()) {
            lista.add(cliente);
            return true;

        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCodigo() == cliente.getCodigo()) {
                    return false;
                }

            }
        }
        lista.add(cliente);
        return true;
    }

    public List<Cliente> organizarLista(){
        List<Cliente> clientesOrdenados = lista.stream().sorted(Comparator.comparing(Cliente::getCodigo)).collect(Collectors.toList());
        return clientesOrdenados;
    }

    public List<Cliente> getLista() {
        return lista;
    }
}
