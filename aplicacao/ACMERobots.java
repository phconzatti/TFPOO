package aplicacao;

import ui.Tela;

import javax.swing.*;

public class ACMERobots  extends JFrame {
    private Tela tela;
    public ACMERobots (){
        super();
        tela = new Tela();
        JPanel painel = tela.getPainel();
        add(painel);
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
