package ui;

import dados.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocacaoUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField numero;
    private JTextField dataInicio;
    private JTextField dataFinal;
    private JTextArea exibeDados;
    private JList<Cliente> listaClientes;
    private JList<Robo> listaRobos;
    private JScrollPane scrollClientes;
    private JScrollPane scrollRobos;
    private int indexCliente, indexRobo;
    RegistroLocacao rl;

    public LocacaoUI(RegistroCliente cliente, RegistroRobo robo, RegistroRobo roboDisponivel, RegistroLocacao locacao) {
        rl = locacao;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        /** Código para a jlist de clientes **/
        Cliente[] clienteArray = new Cliente[cliente.getLista().size()];
        clienteArray = cliente.getLista().toArray(clienteArray);
        listaClientes = new JList<>(clienteArray);
        scrollClientes.setViewportView(listaClientes);

        listaClientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                indexCliente = listaClientes.getSelectedIndex();
            }
        });

        /** Código para a jlist de robos**/
        Robo[] roboArray = new Robo[roboDisponivel.getLista().size()];
        roboArray = roboDisponivel.getLista().toArray(roboArray);
        listaRobos = new JList<>(roboArray);
        scrollRobos.setViewportView(listaRobos);

        listaRobos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                indexRobo = listaRobos.getSelectedIndex();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(numero.getText());
                    int dataI = Integer.parseInt(dataInicio.getText());
                    int dataF = Integer.parseInt(dataFinal.getText());
                    String dataStr = String.format("%08d", dataI);
                    String dataStr2 = String.format("%08d", dataF);
                    SimpleDateFormat dataIFormatada = new SimpleDateFormat("ddMMyyyy");
                    Date data = dataIFormatada.parse(dataStr);
                    Date dataFim = dataIFormatada.parse(dataStr2);
                    Locacao l = new Locacao(num,Status.CADASTRADA, data, dataF);
                    if (!rl.cadastraLocacao(l)){
                        exibeDados.setText("Locação não cadastrada, número repetido.");
                    } else {
                        exibeDados.setText("Locação cadastrada com sucesso.");
                        l.setCliente(cliente.getLista().get(indexCliente));
                        cliente.getLista().get(indexCliente).setQuantidadeRobos(cliente.getLista().get(indexCliente).getQuantidadeRobos() + 1);
                        l.setRobo(roboDisponivel.getLista().get(indexRobo));
                        l.calculaValorFinal(cliente.getLista().get(indexCliente), roboDisponivel.getLista().get(indexRobo));
                        roboDisponivel.getLista().remove(indexRobo);
                        Robo[] roboArray = new Robo[roboDisponivel.getLista().size()];
                        roboArray = roboDisponivel.getLista().toArray(roboArray);
                        listaRobos = new JList<>(roboArray);
                        scrollRobos.setViewportView(listaRobos);
                        rl.cadastraLocacao(l);
                    }
                } catch (NumberFormatException nfe){
                    exibeDados.setText("ERRO! Verifique os campos e insira os dados no formato correto.");
                } catch (ParseException pe){
                    exibeDados.setText("ERRO! Confira os dados inseridos nos campos de datas.");
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
        RegistroLocacao rl = new RegistroLocacao();
        LocacaoUI dialog = new LocacaoUI(rc, rb, rd, rl);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
