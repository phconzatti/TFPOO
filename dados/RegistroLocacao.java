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
            fila.add(locacao);
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

    public List<Locacao> getLista() {
        return lista;
    }

    public void setLista(List<Locacao> lista) {
        this.lista = lista;
    }

    public Queue<Locacao> getFila() {
        return fila;
    }

    public void setFila(Queue<Locacao> fila) {
        this.fila = fila;
    }

}
