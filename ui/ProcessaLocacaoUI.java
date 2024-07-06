package ui;

import dados.Locacao;
import dados.RegistroLocacao;
import dados.Status;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessaLocacaoUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea exibeDados;

    public ProcessaLocacaoUI(RegistroLocacao locacao) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Locacao> executadas = new ArrayList<>();
                if (locacao.getFila().isEmpty()){
                    exibeDados.setText("Nenuma locação realizada.");
                } else {
                    while (!locacao.getFila().isEmpty()) {
                        Locacao l = locacao.getFila().poll();
                        if (l.getSituacao()!=Status.CANCELADA && l.getSituacao()!=Status.FINALIZADA) {
                            l.setSituacao(Status.EXECUTANDO);
                        }
                        executadas.add(l);
                    }
                    locacao.setLista(executadas);
                    for (Locacao lo : locacao.getLista()) {
                        locacao.getFila().add(lo);
                    }
                    exibeDados.setText("Locações executadas.");
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
        dispose();
    }

    public static void main(String[] args) {
        RegistroLocacao rl = new RegistroLocacao();
        ProcessaLocacaoUI dialog = new ProcessaLocacaoUI(rl);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
