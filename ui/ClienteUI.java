package ui;

import dados.Cliente;
import dados.Empresarial;
import dados.Individual;
import dados.RegistroCliente;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClienteUI extends JDialog {
    private JPanel contentPane;
    private JButton adicionar;
    private JButton buttonCancel;
    private JTextField codigo;
    private JTextField nome;
    private JTextField anoCpf;
    private JRadioButton individual;
    private JRadioButton empresarial;
    private JTextArea exibeDados;
    RegistroCliente rc;

    public ClienteUI(RegistroCliente registro) {
        rc = registro;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(adicionar);

        adicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

    private void onOK() {
        try {
            int codigoTexto = Integer.parseInt(codigo.getText());
            String nomeTexto = nome.getText();

            if (individual.isSelected()) {
                String cpfTexto = anoCpf.getText();
                Individual i = new Individual(codigoTexto, nomeTexto, cpfTexto);

                if (!rc.cadastraCliente(i)) {
                    exibeDados.append("Cliente não cadastrado, código repetido.\n");
                } else {
                    exibeDados.append("Cliente cadastrado com sucesso!\n");
                    rc.cadastraCliente(i);
                }
            }

            if (empresarial.isSelected()) {
                int anoTexto = Integer.parseInt(anoCpf.getText());
                Empresarial empresa = new Empresarial(codigoTexto, nomeTexto, anoTexto);
                if (!rc.cadastraCliente(empresa)) {
                    exibeDados.append("Cliente não cadastrado, código repetido.\n");
                } else {
                    exibeDados.append("Cliente cadastrado com sucesso!\n");
                    rc.cadastraCliente(empresa);
                }
            }
        } catch (NumberFormatException nfe){
            exibeDados.append("Formato errado em dado de entrada. Verificar se o código e/ou ano são números inteiros");
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public RegistroCliente getRc(){
        return rc;
    }

    public static void main(String[] args) {
        RegistroCliente reg = new RegistroCliente();
        ClienteUI dialog = new ClienteUI(reg);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
