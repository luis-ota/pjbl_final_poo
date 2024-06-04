package view;

import controller.PacienteController;
import controller.ProfissionalController;
import models.Paciente;
import models.Profissional;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MedicalyInterface extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel doctorsPanel;
    private JPanel addDoctorPanel;
    private JPanel pacientesPanel;
    private JPanel addPacientePanel;

    public MedicalyInterface() {
        setTitle("Medica.ly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        JLabel headerLabel = new JLabel("Medica.ly", JLabel.LEFT);
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        headerLabel.setBackground(new Color(0x77B0FD));
        headerLabel.setOpaque(true);

        JButton agendarConsultaButton = new JButton("Tabela Médico");
        JButton pacientesButton = new JButton("Tabela Paciente");
        JButton adicionarMedicoButton = new JButton("Adicionar Médico");
        JButton adicionarPacienteButton = new JButton("Adicionar Paciente");

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(Box.createVerticalStrut(50));
        sidePanel.add(agendarConsultaButton);
        sidePanel.add(pacientesButton);
        sidePanel.add(adicionarMedicoButton);
        sidePanel.add(adicionarPacienteButton);


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Página Inicial"));

        JPanel agendarConsultaPanel = new JPanel(new BorderLayout());
        JPanel pacientesConsultaPanel = new JPanel(new BorderLayout());

        doctorsPanel = new JPanel(new GridBagLayout());
        pacientesPanel = new JPanel(new GridBagLayout());

        setPacientes();
        setDoutores();

        JButton inicioButton = new JButton("Voltar");
        inicioButton.addActionListener(this);

        agendarConsultaPanel.add(doctorsPanel, BorderLayout.NORTH);
        agendarConsultaPanel.add(inicioButton, BorderLayout.SOUTH);

        pacientesConsultaPanel.add(pacientesPanel, BorderLayout.NORTH);
        pacientesConsultaPanel.add(inicioButton, BorderLayout.SOUTH);

        cardPanel.add(homePanel, "Home");
        cardPanel.add(agendarConsultaPanel, "Tabela Médico");
        cardPanel.add(pacientesConsultaPanel, "Tabela Paciente");
        cardPanel.add(getInputMedico(), "Adicionar Médico");
        cardPanel.add(getInputPaciente(), "Adicionar Paciente");

        agendarConsultaButton.addActionListener(this);
        pacientesButton.addActionListener(this);
        adicionarMedicoButton.addActionListener(this);
        adicionarPacienteButton.addActionListener(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel getInputMedico() {
        addDoctorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomeMedicoLabel = new JLabel("Nome do Médico:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addDoctorPanel.add(nomeMedicoLabel, gbc);

        JTextField nomeMedicoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addDoctorPanel.add(nomeMedicoField, gbc);

        JLabel sobrenomeMedicoLabel = new JLabel("Sobrenome do Médico:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        addDoctorPanel.add(sobrenomeMedicoLabel, gbc);

        JTextField sobrenomeMedicoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addDoctorPanel.add(sobrenomeMedicoField, gbc);

        JLabel especialidadeMedicoLabel = new JLabel("Especialidade:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        addDoctorPanel.add(especialidadeMedicoLabel, gbc);

        JTextField especialidadeMedicoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addDoctorPanel.add(especialidadeMedicoField, gbc);

        JButton adicionarMedicoConfirmButton = new JButton("Adicionar Profissional");
        gbc.gridx = 1;
        gbc.gridy = 3;
        addDoctorPanel.add(adicionarMedicoConfirmButton, gbc);

        adicionarMedicoConfirmButton.addActionListener(e -> {
            String nome = nomeMedicoField.getText();
            String sobrenome = sobrenomeMedicoField.getText();
            String especialidade = especialidadeMedicoField.getText();
            if (!nome.isEmpty() && !especialidade.isEmpty() && !sobrenome.isEmpty()) {
                ProfissionalController controller = new ProfissionalController();
                Profissional novoMedico = new Profissional(nome, especialidade, sobrenome);
                System.out.println(novoMedico.getNomeCompleto());
                controller.adicionar(novoMedico);
                addDoctor(novoMedico, doctorsPanel.getComponentCount()); // Add to panel
                nomeMedicoField.setText("");
                especialidadeMedicoField.setText("");
                sobrenomeMedicoField.setText("");
                cardLayout.show(cardPanel, "Tabela Médico"); // Switch back to doctor list
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return addDoctorPanel;
    }

    private JPanel getInputPaciente() {
        addPacientePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomePacienteLabel = new JLabel("Nome do Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addPacientePanel.add(nomePacienteLabel, gbc);

        JTextField nomePacienteField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addPacientePanel.add(nomePacienteField, gbc);

        JLabel sobrenomePacienteLabel = new JLabel("Sobrenome do Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        addPacientePanel.add(sobrenomePacienteLabel, gbc);

        JTextField sobrenomePacienteField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addPacientePanel.add(sobrenomePacienteField, gbc);

        JLabel cpfPacienteLabel = new JLabel("Telefone:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        addPacientePanel.add(cpfPacienteLabel, gbc);

        JTextField cpfPacienteField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addPacientePanel.add(cpfPacienteField, gbc);

        JButton adicionarPacienteConfirmButton = new JButton("Adicionar Paciente");
        gbc.gridx = 1;
        gbc.gridy = 3;
        addPacientePanel.add(adicionarPacienteConfirmButton, gbc);

        adicionarPacienteConfirmButton.addActionListener(e -> {
            String nome = nomePacienteField.getText();
            String sobrenome = sobrenomePacienteField.getText();
            String cpf = cpfPacienteField.getText();
            if (!nome.isEmpty() && !cpf.isEmpty() && !sobrenome.isEmpty()) {
                PacienteController controller = new PacienteController();
                Paciente novoPaciente = new Paciente(nome, sobrenome, cpf);
                System.out.println(novoPaciente.getNomeCompleto());
                controller.adicionar(novoPaciente);
                addPaciente(novoPaciente, pacientesPanel.getComponentCount()); // Add to panel
                nomePacienteField.setText("");
                sobrenomePacienteField.setText("");
                cpfPacienteField.setText("");
                cardLayout.show(cardPanel, "Tabela Paciente"); // Switch back to patient list
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return addPacientePanel;
    }

    private void setDoutores() {
        ProfissionalController controller = new ProfissionalController();
        ArrayList<Profissional> profissionais = controller.listar();
        int row = 0;
        for (Profissional profissional : profissionais) {
            addDoctor(profissional, row++);
        }
    }

    private void setPacientes() {
        PacienteController controller = new PacienteController();
        ArrayList<Paciente> pacientes = controller.listar();
        int row = 0;
        for (Paciente paciente : pacientes) {
            addPaciente(paciente, row++);
        }
    }

    private void addDoctor(Profissional p, int row) {
        JPanel doctorSquare = createDoctorSquare(p.getNomeCompleto(), p.getEspecialidades());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        doctorsPanel.add(doctorSquare, gbc);
    }

    private void addPaciente(Paciente p, int row) {
        JPanel pacienteSquare = createPacienteSquare(p.getNomeCompleto(), p.getCpf());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        pacientesPanel.add(pacienteSquare, gbc);
    }

    private JPanel createPacienteSquare(String name, String cpf) {
        JPanel pacientePanel = new JPanel(new BorderLayout());
        pacientePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(name);
        JLabel cpfLabel = new JLabel(new String("          " + cpf));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton editar = new JButton("Editar");
        JButton deletar = new JButton("Deletar");

        buttonPanel.add(editar);
        buttonPanel.add(deletar);

        pacientePanel.add(nameLabel, BorderLayout.WEST);
        pacientePanel.add(cpfLabel, BorderLayout.CENTER);
        pacientePanel.add(buttonPanel, BorderLayout.EAST);

        return pacientePanel;
    }

    private JPanel createDoctorSquare(String name, String specialty) {
        JPanel doctorPanel = new JPanel(new BorderLayout());
        doctorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(name);
        JLabel specialtyLabel = new JLabel(new String("          " + specialty));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton editar = new JButton("Editar");
        JButton deletar = new JButton("Deletar");

        buttonPanel.add(editar);
        buttonPanel.add(deletar);

        doctorPanel.add(nameLabel, BorderLayout.WEST);
        doctorPanel.add(specialtyLabel, BorderLayout.CENTER);
        doctorPanel.add(buttonPanel, BorderLayout.EAST);

        return doctorPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Tabela Médico")) {
            cardLayout.show(cardPanel, "Tabela Médico");
        } else if (e.getActionCommand().equals("Tabela Paciente")) {
            cardLayout.show(cardPanel, "Tabela Paciente");
        } else if (e.getActionCommand().equals("Adicionar Médico")) {
            cardLayout.show(cardPanel, "Adicionar Médico");
        } else if (e.getActionCommand().equals("Adicionar Paciente")) {
            cardLayout.show(cardPanel, "Adicionar Paciente");
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
