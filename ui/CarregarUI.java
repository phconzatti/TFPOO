package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.poo.typeadapters.RuntimeTypeAdapterFactory;
import dados.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CarregarUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea exibeDados;
    private JTextField nomeArquivo;
    private String nome;

    public CarregarUI(RegistroCliente cliente, RegistroRobo robo, RegistroRobo roboDisponivel) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nome = nomeArquivo.getText();
                try (FileReader reader = new FileReader(nome+"-CLIENTES.json")) {
                    RuntimeTypeAdapterFactory<Cliente> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                            .of(Cliente.class, "tipo")
                            .registerSubtype(Individual.class, "1")
                            .registerSubtype(Empresarial.class, "2");
                    Gson gson2 = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                            .create();
                    java.lang.reflect.Type pessoaListType = new TypeToken<List<Cliente>>() {}.getType();
                    List<Cliente> pessoas = gson2.fromJson(reader, pessoaListType);
                    for (int i = 0; i< pessoas.size(); i++){
                        cliente.cadastraCliente(pessoas.get(i));
                    }
                FileReader reader1 = new FileReader(nome+"-ROBOS.json");
                    RuntimeTypeAdapterFactory<Robo> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory
                            .of(Robo.class, "tipo")
                            .registerSubtype(Domestico.class, "1")
                            .registerSubtype(Industrial.class, "2")
                            .registerSubtype(Agricola.class, "3");
                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory1)
                            .create();
                    java.lang.reflect.Type roboListType = new TypeToken<List<Robo>>() {
                    }.getType();
                    List<Robo> roboLista = gson.fromJson(reader1, roboListType);
                    for (int i = 0; i < roboLista.size(); i++) {
                        robo.cadastraRobo(roboLista.get(i));
                        roboDisponivel.cadastraRobo(roboLista.get(i));
                    }
                    exibeDados.setText("Dados carregados com sucesso!");
                } catch (FileNotFoundException f){
                    exibeDados.setText("Arquivo inexistente.");
                }
                catch (IOException j) {
                    exibeDados.setText("Arquivo inexistente.");
                }
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
        CarregarUI dialog = new CarregarUI(rc, rb, rd);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
