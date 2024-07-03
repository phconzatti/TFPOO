package dados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RegistroLocacao {
    private List<Locacao> lista;
    private Queue<Locacao> fila;

    public RegistroLocacao (){
        lista = new ArrayList<>();
        fila = new LinkedList<>();
    }

    public boolean cadastraLocacao(Locacao locacao){
        if (lista.isEmpty()) {
            lista.add(locacao);
            return true;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNumero() == locacao.getNumero()) {
                    return false;
                }

            }
        }
        lista.add(locacao);
        fila.add(locacao);
        return true;
    }
}
