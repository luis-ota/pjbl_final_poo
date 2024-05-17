package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicalyInterface extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel doctorsPanel;

    public MedicalyInterface() {

        setTitle("Medica.ly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JLabel headerLabel = new JLabel("Medica.ly", JLabel.LEFT);
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        headerLabel.setBackground(new Color(0x77B0FD));
        headerLabel.setOpaque(true);

        JButton agendarConsultaButton = new JButton("Agendar Consulta");
        JButton sobreButton = new JButton("Sobre");
        JButton minhasConsultasButton = new JButton("Minhas Consultas");
        JButton meusResultadosConsultas = new JButton("Meus Resultados");

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(Box.createVerticalStrut(50));
        sidePanel.add(agendarConsultaButton);
        sidePanel.add(sobreButton);
        sidePanel.add(minhasConsultasButton);
        sidePanel.add(meusResultadosConsultas);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Página Inicial"));

        JPanel agendarConsultaPanel = new JPanel(new BorderLayout());
        addConsulta("Consulta", "Cardiologista");

        doctorsPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        addDoctor("Dr. Gustavo", "Cardiologista");
        addDoctor("Dra. Luis", "Dermatologista");
        addDoctor("Dr. Victor", "Clínico Geral");

        JButton inicioButton = new JButton("Voltar");
        inicioButton.addActionListener(this);

        agendarConsultaPanel.add(doctorsPanel, BorderLayout.CENTER);
        agendarConsultaPanel.add(inicioButton, BorderLayout.SOUTH);

        cardPanel.add(homePanel, "Home");
        cardPanel.add(agendarConsultaPanel, "Agendar Consulta");

        agendarConsultaButton.addActionListener(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void addConsulta(String consulta, String cardiologista) {
    }

    private void addDoctor(String name, String especialidade) {
        JPanel doctorSquare = createDoctorSquare(name, especialidade);
        doctorsPanel.add(doctorSquare);
    }

    private JPanel createDoctorSquare(String name, String specialty) {
        JPanel doctorPanel = new JPanel(new BorderLayout());
        doctorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel nameLabel = new JLabel(name, JLabel.CENTER);
        JLabel specialtyLabel = new JLabel(specialty, JLabel.CENTER);
        doctorPanel.add(nameLabel, BorderLayout.CENTER);
        doctorPanel.add(specialtyLabel, BorderLayout.SOUTH);
        return doctorPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agendar Consulta")) {
            cardLayout.show(cardPanel, "Agendar Consulta");
        } else if (e.getActionCommand().equals("Voltar")) {
            cardLayout.show(cardPanel, "Home");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MedicalyInterface interfaceMedicaly = new MedicalyInterface();
            interfaceMedicaly.setVisible(true);
        });
    }
}
