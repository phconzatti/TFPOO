package ui;

import dados.Cliente;
import dados.RegistroCliente;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Relatorio extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextArea relatorio;
    private JButton imprimir;

    public Relatorio(RegistroCliente cliente) {
        setContentPane(contentPane);
        setModal(true);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        imprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> reg = cliente.organizarLista();
                StringBuilder sb = new StringBuilder();
                for (Cliente c : reg){
                    sb.append(c);
                }
                String exibir = sb.toString();
                relatorio.setText(exibir);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
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
        RegistroCliente rc = new RegistroCliente();
        Relatorio dialog = new Relatorio(rc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
