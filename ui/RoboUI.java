package ui;

import dados.Agricola;
import dados.Domestico;
import dados.Industrial;
import dados.RegistroRobo;

import javax.swing.*;
import java.awt.event.*;

public class RoboUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField id;
    private JTextField modelo;
    private JTextField valorDiario;
    private JRadioButton domestico;
    private JRadioButton industrial;
    private JRadioButton agricola;
    private JTextField nivel;
    private JTextField setor;
    private JTextField area;
    private JTextField uso;
    private JTextArea exibeDados;
    private JButton limpar;
    RegistroRobo rb;
    ButtonGroup b;

    public RoboUI(RegistroRobo registro) {
        b = new ButtonGroup();
        b.add(domestico);
        b.add(industrial);
        b.add(agricola);
        rb = registro;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.clearSelection();
                id.setText("");
                modelo.setText("");
                valorDiario.setText("");
                nivel.setText("");
                setor.setText("");
                area.setText("");
                uso.setText("");
                exibeDados.setText("");
            }
        });

        buttonOK.addActionListener(new ActionListener() {
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
    }

    private void onOK() {
        int idTexto = 0;
        String modeloTexto = null;
        double valorDiarioTexto = 0;
        int nivelTexto = 0;

        try{
            idTexto = Integer.parseInt(id.getText());
            modeloTexto = modelo.getText();
            valorDiarioTexto = Double.parseDouble(valorDiario.getText());
        } catch (NumberFormatException nfe){
            exibeDados.append("Formato incorreto no(s) campo(s) 'ID' e/ou 'Valor diário'.\n");
        }
        if (idTexto == 0 || modeloTexto == null || valorDiarioTexto == 0){
            exibeDados.append("ERRO! Dados necessários não inseridos.\n");
        } else {
            if (domestico.isSelected()) {
                try {
                    nivelTexto = Integer.parseInt(nivel.getText());
                } catch (NumberFormatException nfe2) {
                    exibeDados.append("Formato incorreto no campo 'Nível'.\n");
                }
                Domestico d = new Domestico(idTexto, modeloTexto, valorDiarioTexto, nivelTexto);
                if (!rb.cadastraRobo(d)) {
                    exibeDados.append("Robô não cadastrado, ID repetido.\n");
                } else {
                    exibeDados.append("Robô cadastrado com sucesso.\n");
                    rb.cadastraRobo(d);
                }
            }
            if (industrial.isSelected()) {
                String setorTexto = setor.getText();
                Industrial i = new Industrial(idTexto, modeloTexto, valorDiarioTexto, setorTexto);
                if (!rb.cadastraRobo(i)) {
                    exibeDados.append("Robô não cadastrado, ID repetido.\n");
                } else {
                    exibeDados.append("Robô cadastrado com sucesso.\n");
                    rb.cadastraRobo(i);
                }
            }
            if (agricola.isSelected()) {
                double areaTexto = 0;
                String usoTexto = null;
                try {
                    areaTexto = Double.parseDouble(area.getText());
                    usoTexto = uso.getText();
                } catch (NumberFormatException nfe3){
                    exibeDados.append("Formato incorreto no campo 'Área'.\n");
                }
                Agricola a = new Agricola(idTexto, modeloTexto, valorDiarioTexto, areaTexto, usoTexto);
                if (!rb.cadastraRobo(a)) {
                    exibeDados.append("Robô não cadastrado, ID repetido.\n");
                } else {
                    exibeDados.append("Robô cadastrado com sucesso.\n");
                    rb.cadastraRobo(a);
                }

            }
        }
    }

    private void onCancel() {
        dispose();
    }

    public RegistroRobo getRb(){
        return rb;
    }

    public static void main(String[] args) {
        RegistroRobo reg = new RegistroRobo();
        RoboUI dialog = new RoboUI(reg);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
