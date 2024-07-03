package ui;

import com.google.gson.Gson;
import dados.Cliente;
import dados.RegistroCliente;
import dados.RegistroRobo;
import dados.Robo;

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

    public SalvarUI(RegistroCliente cliente, RegistroRobo robo) {
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
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        SalvarUI dialog = new SalvarUI(rc, rb);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
