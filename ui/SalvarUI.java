package ui;

import com.google.gson.Gson;
import dados.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvarUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nomeArquivo;
    private JTextArea exibeDados;
    private String arquivoNome = null;

    public SalvarUI(RegistroCliente cliente, RegistroRobo robo, RegistroLocacao locacao, RegistroRobo roboDisponivel) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arquivoNome = nomeArquivo.getText();
                    List<Cliente> salvaCliente = cliente.organizarLista();
                    Gson gson = new Gson();
                    String json = gson.toJson(salvaCliente);
                    try (FileWriter writer = new FileWriter(arquivoNome + "-CLIENTES.json")) {
                        writer.write(json);
                    } catch (IOException h) {
                        h.printStackTrace();
                    }
                    List<Robo> salvaRobo = robo.organizarLista();
                    Gson gson2 = new Gson();
                    String json2 = gson.toJson(salvaRobo);
                    try (FileWriter writer = new FileWriter(arquivoNome + "-ROBOS.json")) {
                        writer.write(json2);
                    } catch (IOException h) {
                        h.printStackTrace();
                    }
                    List<Locacao> salvaLocacao = locacao.getLista();
                    Gson gson3 = new Gson();
                    String json3 = gson.toJson(salvaLocacao);
                    try (FileWriter writer = new FileWriter(arquivoNome + "-LOCACAO.json")) {
                    writer.write(json3);
                    } catch (IOException h) {
                        h.printStackTrace();
                    }
                    List<Robo> salvaRobo2 = roboDisponivel.organizarLista();
                    Gson gson4 = new Gson();
                    String json4 = gson.toJson(salvaRobo2);
                    try (FileWriter writer = new FileWriter(arquivoNome + "-ROBOSDISPONIVEIS.json")) {
                    writer.write(json4);
                    } catch (IOException h) {
                        h.printStackTrace();
                    }
                    exibeDados.append("Dados salvos com sucesso!");
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
        RegistroRobo rd = new RegistroRobo();
        RegistroLocacao rl = new RegistroLocacao();
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        SalvarUI dialog = new SalvarUI(rc, rb, rl,rd);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
