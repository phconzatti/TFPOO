package dados;

import java.util.ArrayList;
import java.util.List;

public class RegistroLocacao {
    private List<Locacao> lista;

    public RegistroLocacao (){
        lista = new ArrayList<>();
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
        return true;
    }
}
