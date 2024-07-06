package ui;

import dados.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class AlteraLocacaoUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Locacao> listaLocacao;
    private JScrollPane scrollLocacao;
    private JRadioButton cancela;
    private JRadioButton finaliza;
    private JTextArea exibeDados;
    private int locacaoIndex;
    private ButtonGroup b;


    public AlteraLocacaoUI(RegistroCliente cliente, RegistroRobo robo, RegistroRobo roboDisponivel, RegistroLocacao locacao) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        b = new ButtonGroup();
        b.add(cancela);
        b.add(finaliza);


        Locacao[] locacaoArray = new Locacao[locacao.getFila().size()];
        locacaoArray = locacao.getFila().toArray(locacaoArray);
        listaLocacao = new JList<>(locacaoArray);
        scrollLocacao.setViewportView(listaLocacao);

        if(locacao.getFila().isEmpty()){
            exibeDados.setText("Nenhuma locação registrada.");
        }

        listaLocacao.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                locacaoIndex = listaLocacao.getSelectedIndex();

            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Queue<Locacao> filaNova = new LinkedList<>();
                if(locacao.getFila().isEmpty()){
                    exibeDados.setText("Nenhuma locação registrada.");
                } else {
                    if (finaliza.isSelected()){
                        if (locacao.getLista().get(locacaoIndex).getSituacao() == Status.FINALIZADA || locacao.getLista().get(locacaoIndex).getSituacao() == Status.CANCELADA){
                            exibeDados.setText("Locação não pode ser modificada.");
                        } else {
                            locacao.getLista().get(locacaoIndex).setSituacao(Status.FINALIZADA);
                            exibeDados.setText("Mudança realizada com sucesso.");
                        }
                    } else {
                        if (locacao.getLista().get(locacaoIndex).getSituacao() == Status.FINALIZADA || locacao.getLista().get(locacaoIndex).getSituacao() == Status.CANCELADA){
                            exibeDados.setText("Locação não pode ser modificada.");
                        } else {
                            locacao.getLista().get(locacaoIndex).setSituacao(Status.CANCELADA);
                            exibeDados.setText("Mudança realizada com sucesso.");
                        }
                    }
                }
                for (Locacao l:locacao.getLista()){
                    if (l.getSituacao() != Status.CANCELADA && l.getSituacao() != Status.FINALIZADA){
                        filaNova.add(l);
                        locacao.setFila(filaNova);
                    }
                }
                for (Locacao l2:locacao.getLista()){
                    if (l2.getSituacao() == Status.CANCELADA || l2.getSituacao() == Status.FINALIZADA){
                        String c = l2.getCliente();
                        for (Cliente cl:cliente.getLista()){
                            if (cl.getNome().equalsIgnoreCase(c)){
                                cl.setQuantidadeRobos(cl.getQuantidadeRobos()-1);
                            }
                        }
                        for (Robo r: robo.getLista()){
                            if (l2.getRobo()==r.getId()){
                                roboDisponivel.cadastraRobo(r);
                            }
                        }
                    }
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        RegistroLocacao rl = new RegistroLocacao();
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        RegistroRobo rd = new RegistroRobo();
        AlteraLocacaoUI dialog = new AlteraLocacaoUI(rc, rb, rd, rl);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
