package dados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroRobo {

        private List<Robo> lista;

        public RegistroRobo (){
            lista = new ArrayList<>();
        }

        public boolean cadastraCliente(Robo robo) {
            if (lista.isEmpty()) {
                lista.add(robo);
                return true;

            } else {
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getId() == robo.getId()) {
                        return false;
                    }

                }
            }
            lista.add(robo);
            return true;
        }

        public List<Robo> organizarLista(){
            List<Robo> robosOrdenados = lista.stream().sorted(Comparator.comparing(Robo::getId)).collect(Collectors.toList());
            return robosOrdenados;
        }

        public List<Robo> getLista() {
            return lista;
        }
    }


