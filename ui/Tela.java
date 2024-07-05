package ui;

import dados.*;

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
    private RegistroCliente cliente;
    private RegistroRobo robo;
    private RegistroRobo roboDisponivel;
    private RegistroLocacao locacao;

    public Tela() {
        roboDisponivel = new RegistroRobo();
        cliente = new RegistroCliente();
        robo = new RegistroRobo();
        locacao = new RegistroLocacao();

        processaLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessaLocacaoUI processaLocacaoUI = new ProcessaLocacaoUI(locacao);
                processaLocacaoUI.setTitle("Processar as locações");
                processaLocacaoUI.setSize(800,600);
                processaLocacaoUI.setModal(true);
                processaLocacaoUI.setVisible(true);
            }
        });

        alteraLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cargaDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaUI cargaUI = new CargaUI(cliente, robo, roboDisponivel, locacao);
                cargaUI.setTitle("Carregar dados iniciais");
                cargaUI.setSize(800,600);
                cargaUI.setModal(true);
                cargaUI.setVisible(true);
            }
        });

        consultaLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultaLocacaoUI consultaLocacaoUI = new ConsultaLocacaoUI(cliente, robo, roboDisponivel, locacao);
                consultaLocacaoUI.setTitle("Consulta de locações");
                consultaLocacaoUI.setSize(800,600);
                consultaLocacaoUI.setModal(true);
                consultaLocacaoUI.setVisible(true);
            }
        });

        cadastraLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocacaoUI locacaoUI = new LocacaoUI(cliente, robo, roboDisponivel, locacao);
                locacaoUI.setTitle("Cadastrar nova locação");
                locacaoUI.setSize(800,600);
                locacaoUI.setModal(true);
                locacaoUI.setVisible(true);
            }
        });

        cadastraRobo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoboUI roboUI = new RoboUI(robo, roboDisponivel);
                roboUI.setTitle("Cadastrar novo robô");
                roboUI.setSize(800,600);
                roboUI.setModal(true);
                roboUI.setVisible(true);
            }
        });

        cadastraCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteUI clienteUI = new ClienteUI(cliente);
                clienteUI.setTitle("Cadastrar novo cliente");
                clienteUI.setSize(800,600);
                clienteUI.setModal(true);
                clienteUI.setVisible(true);
            }
        });

        relatorioGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioUI relatorioUI = new RelatorioUI(cliente, robo, locacao);
                relatorioUI.setTitle("Relatório");
                relatorioUI.setSize(800,600);
                relatorioUI.setModal(true);
                relatorioUI.setVisible(true);
            }
        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalvarUI salvarUI = new SalvarUI(cliente, robo, locacao, roboDisponivel);
                salvarUI.setTitle("Salvar dados");
                salvarUI.setSize(800,600);
                salvarUI.setModal(true);
                salvarUI.setVisible(true);
            }
        });

        carrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarregarUI carregarUI = new CarregarUI(cliente, robo, roboDisponivel, locacao);
                carregarUI.setTitle("Carregar dados");
                carregarUI.setSize(800,600);
                carregarUI.setModal(true);
                carregarUI.setVisible(true);
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

