package ui;

import dados.Cliente;
import dados.RegistroCliente;
import dados.RegistroRobo;
import dados.Robo;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Relatorio extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextArea relatorio;
    private JButton imprimir;

    public Relatorio(RegistroCliente cliente, RegistroRobo robo) {
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

                List<Robo> reg2 = robo.organizarLista();
                StringBuilder sb2 = new StringBuilder();
                for (Robo r : reg2){
                    sb2.append(r);
                }
                String exibir2 = sb2.toString();

                relatorio.setText("Clientes: \n"+exibir+"\nRobôs: \n"+exibir2);
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
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        Relatorio dialog = new Relatorio(rc, rb);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
