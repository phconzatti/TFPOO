package ui;

import com.google.gson.reflect.TypeToken;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;


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

    public Tela() {
        cliente = new RegistroCliente();
        robo = new RegistroRobo();

        cadastraRobo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoboUI roboUI = new RoboUI(robo);
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
                Relatorio relatorio = new Relatorio(cliente, robo);
                relatorio.setTitle("Relatório");
                relatorio.setSize(800,600);
                relatorio.setModal(true);
                relatorio.setVisible(true);
            }
        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> salvaCliente = cliente.organizarLista();
                Gson gson = new Gson();
                String json = gson.toJson(salvaCliente);
                try (FileWriter writer = new FileWriter("cliente.json")) {
                    writer.write(json);
                } catch (IOException h) {
                    h.printStackTrace();
                }
                List<Robo> salvaRobo = robo.organizarLista();
                Gson gson2 = new Gson();
                String json2 = gson.toJson(salvaRobo);
                try (FileWriter writer = new FileWriter("robo.json")) {
                    writer.write(json2);
                } catch (IOException h) {
                    h.printStackTrace();
                }

            }
        });

        carrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gson gson = new Gson();
                try (FileReader reader = new FileReader("cliente.json")) {
                    Type pessoaListType = new TypeToken<List<Individual>>() {}.getType();
                    List<Individual> pessoas = gson.fromJson(reader, pessoaListType);
                    for (int i = 0; i< pessoas.size(); i++){
                        cliente.cadastraCliente(pessoas.get(i));
                    }
                } catch (IOException j) {
                    j.printStackTrace();
                }

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

