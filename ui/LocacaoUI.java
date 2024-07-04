package ui;

import dados.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class LocacaoUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea exibeDados;
    private JList<Cliente> listaClientes;
    private JList<Robo> listaRobos;
    private JScrollPane scrollClientes;
    private JScrollPane scrollRobos;

    public LocacaoUI(RegistroCliente cliente, RegistroRobo robo, RegistroRobo roboDisponivel, RegistroLocacao locacao) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Cliente[] clienteArray = new Cliente[cliente.getLista().size()];
        clienteArray = cliente.getLista().toArray(clienteArray);
        listaClientes = new JList<>(clienteArray);
        scrollClientes.setViewportView(listaClientes);

        Robo[] roboArray = new Robo[roboDisponivel.getLista().size()];
        roboArray = roboDisponivel.getLista().toArray(roboArray);
        listaRobos = new JList<>(roboArray);
        scrollRobos.setViewportView(listaRobos);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        RegistroRobo rd = new RegistroRobo();
        RegistroLocacao rl = new RegistroLocacao();
        LocacaoUI dialog = new LocacaoUI(rc, rb, rd, rl);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
