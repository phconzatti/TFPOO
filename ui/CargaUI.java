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
import java.util.List;

public class CargaUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea exibeDados;
    private JTextField nomeArquivo;

    public CargaUI(RegistroCliente cliente, RegistroRobo robo, RegistroRobo roboDisponivel, RegistroLocacao locacao) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeArquivo.getText();
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
                    }

                    FileReader reader4 = new FileReader(nome+"-ROBOSDISPONIVEIS.json");
                    RuntimeTypeAdapterFactory<Robo> runtimeTypeAdapterFactory4 = RuntimeTypeAdapterFactory
                            .of(Robo.class, "tipo")
                            .registerSubtype(Domestico.class, "1")
                            .registerSubtype(Industrial.class, "2")
                            .registerSubtype(Agricola.class, "3");
                    Gson gson4 = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory4)
                            .create();
                    java.lang.reflect.Type roboListType4 = new TypeToken<List<Robo>>() {
                    }.getType();
                    List<Robo> roboLista4 = gson4.fromJson(reader4, roboListType4);
                    for (int i = 0; i < roboLista4.size(); i++) {
                        roboDisponivel.cadastraRobo(roboLista4.get(i));
                    }

                    FileReader reader2 = new FileReader(nome+"-LOCACAO.json");
                    RuntimeTypeAdapterFactory<Locacao> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory
                            .of(Locacao.class, "numero");
                    Gson gson3 = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory1)
                            .create();
                    java.lang.reflect.Type locacaoListType = new TypeToken<List<Locacao>>() {
                    }.getType();
                    List<Locacao> locacaoLista = gson3.fromJson(reader2, locacaoListType);
                    for (int i = 0; i < locacaoLista.size(); i++) {
                        locacao.cadastraLocacao(locacaoLista.get(i));
                    }

                    exibeDados.setText("Dados carregados com sucesso!");
                } catch (FileNotFoundException f){
                    exibeDados.setText("Arquivo inexistente.");
                }
                catch (IOException j) {
                    exibeDados.setText("Arquivo inexistente.");
                }

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

                List<Locacao> reg3 = locacao.getLista();
                StringBuilder sb3 = new StringBuilder();
                for (Locacao l : reg3){
                    sb3.append(l);
                }
                String exibir3 = sb3.toString();

                exibeDados.setText("Clientes: \n"+exibir+"\nRobôs: \n"+exibir2+"\nLocações: \n"+exibir3);
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
        RegistroLocacao rl = new RegistroLocacao();
        RegistroCliente rc = new RegistroCliente();
        RegistroRobo rb = new RegistroRobo();
        RegistroRobo rd = new RegistroRobo();
        CargaUI dialog = new CargaUI(rc, rb, rd, rl);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
