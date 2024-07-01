package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela {
    private JButton cadastraRobo;
    private JButton cadastraCliente;
    private JButton cadastraLocacao;
    private JButton processaLocacao;
    private JButton relatorioGeral;
    private JButton consultaLocacao;
    private JButton alteraLocacao;
    private JButton cargaDados;
    private JButton salvar;
    private JButton carrega;
    private JButton sair;
    private JPanel Painel;

    public Tela() {
        cadastraCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteUI clienteUI = new ClienteUI();
                clienteUI.setTitle("Cadastrar novo cliente");
                clienteUI.setSize(800,600);
                clienteUI.setModal(true);
                clienteUI.setVisible(true);
            }
        });
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public JPanel getPainel() {
        return Painel;
    }
}

