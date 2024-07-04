package ui;

import dados.*;

import javax.swing.*;
import java.awt.event.*;

public class ConsultaLocacaoUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea exibeDados;

    public ConsultaLocacaoUI(RegistroCliente cliente, RegistroRobo robo, RegistroRobo roboDisponivel, RegistroLocacao locacao) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                StringBuilder sb = new StringBuilder();
                for (Locacao l : locacao.getLista()){
                    sb.append(l.toString2()+", ");
                    for (Cliente c : cliente.getLista()){
                        if (c.getNome().equalsIgnoreCase(l.getCliente())){
                            sb.append(c.toString2()+", ");
                        }
                    }
                    for (Robo r : robo.getLista()){
                        if (r.getId() == l.getRobo()){
                            sb.append(r.toString2()+";\n");
                        }
                    }
                }
                exibeDados.setText(sb.toString());
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
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        RegistroRobo rd = new RegistroRobo();
        ConsultaLocacaoUI dialog = new ConsultaLocacaoUI(rc, rb, rd, rl);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
