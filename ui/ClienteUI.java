package ui;

import dados.Empresarial;
import dados.Individual;
import dados.RegistroCliente;

import javax.swing.*;
import java.awt.event.*;

public class ClienteUI extends JDialog {
    private JPanel contentPane;
    private JButton adicionar;
    private JButton buttonCancel;
    private JTextField codigo;
    private JTextField anoCpf;
    private JTextField nome;
    private JRadioButton individual;
    private JRadioButton empresarial;
    private JTextArea exibeDados;
    private JButton limpar;
    RegistroCliente rc;
    private ButtonGroup b;

    public ClienteUI(RegistroCliente registro) {
        rc = registro;
        b = new ButtonGroup();
        b.add(empresarial);
        b.add(individual);
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

        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo.setText("");
                anoCpf.setText("");
                nome.setText("");
                exibeDados.setText("");
                b.clearSelection();
            }
        });
    }

    private void onOK() {
        try {
            int codigoTexto = Integer.parseInt(codigo.getText());
            String nomeTexto = nome.getText();

            if (individual.isSelected()) {
                String cpfTexto = anoCpf.getText();
                Individual i = new Individual(codigoTexto, nomeTexto, cpfTexto);
                i.setTipo(1);
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
                empresa.setTipo(2);
                if (!rc.cadastraCliente(empresa)) {
                    exibeDados.append("Cliente não cadastrado, código repetido.\n");
                } else {
                    exibeDados.append("Cliente cadastrado com sucesso!\n");
                    rc.cadastraCliente(empresa);
                }
            }
        } catch (NumberFormatException nfe){
            exibeDados.append("Formato errado em dado de entrada. Verificar se o código e/ou ano são números inteiros.\n");
        }

    }

    private void onCancel() {
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
