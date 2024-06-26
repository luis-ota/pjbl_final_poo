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
    private JPanel editDoctorPanel;
    private JPanel pacientesPanel;
    private JPanel addPacientePanel;
    private JPanel editPacientePanel;

    private ProfissionalController profissionalController = new ProfissionalController();

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
        JPanel addMedicoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomeMedicoLabel = new JLabel("Nome do Médico:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addMedicoPanel.add(nomeMedicoLabel, gbc);

        JTextField nomeMedicoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addMedicoPanel.add(nomeMedicoField, gbc);

        JLabel sobrenomeMedicoLabel = new JLabel("Sobrenome do Médico:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        addMedicoPanel.add(sobrenomeMedicoLabel, gbc);

        JTextField sobrenomeMedicoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addMedicoPanel.add(sobrenomeMedicoField, gbc);

        JLabel especialidadeMedicoLabel = new JLabel("Especialidade:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        addMedicoPanel.add(especialidadeMedicoLabel, gbc);

        JTextField especialidadeMedicoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addMedicoPanel.add(especialidadeMedicoField, gbc);

        JButton adicionarMedicoConfirmButton = new JButton("Adicionar Médico");
        gbc.gridx = 1;
        gbc.gridy = 3;
        addMedicoPanel.add(adicionarMedicoConfirmButton, gbc);

        adicionarMedicoConfirmButton.addActionListener(e -> {
            String nome = nomeMedicoField.getText();
            String sobrenome = sobrenomeMedicoField.getText();
            String especialidade = especialidadeMedicoField.getText();
            if (!nome.isEmpty() && !especialidade.isEmpty() && !sobrenome.isEmpty()) {
                ProfissionalController controller = new ProfissionalController();
                Profissional novoMedico = new Profissional(nome, sobrenome, especialidade);
                controller.adicionar(novoMedico);
                addDoctor(novoMedico, doctorsPanel.getComponentCount());
                nomeMedicoField.setText("");
                especialidadeMedicoField.setText("");
                sobrenomeMedicoField.setText("");
                cardLayout.show(cardPanel, "Tabela Médico");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return addMedicoPanel;
    }


    private JPanel getInputPaciente() {
        JPanel addPacientePanel = new JPanel(new GridBagLayout());
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

        JLabel cpfPacienteLabel = new JLabel("CPF:");
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
                controller.adicionar(novoPaciente);
                addPaciente(novoPaciente, pacientesPanel.getComponentCount());
                nomePacienteField.setText("");
                sobrenomePacienteField.setText("");
                cpfPacienteField.setText("");
                cardLayout.show(cardPanel, "Tabela Paciente");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return addPacientePanel;
    }


    private void setDoutores() {
        ArrayList<Profissional> profissionais = profissionalController.listar();
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
        JPanel doctorSquare = createDoctorSquare(p, row);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        doctorsPanel.add(doctorSquare, gbc);
    }

    private void addPaciente(Paciente p, int row) {
        JPanel pacienteSquare = createPacienteSquare(p);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        pacientesPanel.add(pacienteSquare, gbc);
    }

    private JPanel createPacienteSquare(Paciente paciente) {
        JPanel pacientePanel = new JPanel(new BorderLayout());
        pacientePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(paciente.getNomeCompleto());
        JLabel cpfLabel = new JLabel(new String("          " + paciente.getCpf()));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton editar = new JButton("Editar");
        JButton deletar = new JButton("Deletar");

        buttonPanel.add(editar);
        buttonPanel.add(deletar);

        pacientePanel.add(nameLabel, BorderLayout.WEST);
        pacientePanel.add(cpfLabel, BorderLayout.CENTER);
        pacientePanel.add(buttonPanel, BorderLayout.EAST);

        deletar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja deletar este paciente?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                PacienteController controller = new PacienteController();
                controller.deletar(paciente);
                pacientesPanel.remove(pacientePanel);
                pacientesPanel.revalidate();
                pacientesPanel.repaint();
            }
        });
        editar.addActionListener(e -> {
            showEditPacientePanel(paciente);
        });


        return pacientePanel;
    }

    private void showEditPacientePanel(Paciente paciente) {
        if (editPacientePanel == null) {
            editPacientePanel = new JPanel(new GridBagLayout());
        } else {
            editPacientePanel.removeAll();
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomePacienteLabel = new JLabel("Nome do Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        editPacientePanel.add(nomePacienteLabel, gbc);

        JTextField nomePacienteField = new JTextField(20);
        nomePacienteField.setText(paciente.getNome());
        gbc.gridx = 1;
        gbc.gridy = 0;
        editPacientePanel.add(nomePacienteField, gbc);

        JLabel sobrenomePacienteLabel = new JLabel("Sobrenome do Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        editPacientePanel.add(sobrenomePacienteLabel, gbc);

        JTextField sobrenomePacienteField = new JTextField(20);
        sobrenomePacienteField.setText(paciente.getSobrenome());
        gbc.gridx = 1;
        gbc.gridy = 1;
        editPacientePanel.add(sobrenomePacienteField, gbc);

        JLabel cpfPacienteLabel = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        editPacientePanel.add(cpfPacienteLabel, gbc);

        JTextField cpfPacienteField = new JTextField(20);
        cpfPacienteField.setText(paciente.getCpf());
        gbc.gridx = 1;
        gbc.gridy = 2;
        editPacientePanel.add(cpfPacienteField, gbc);

        JButton atualizarPacienteConfirmButton = new JButton("Atualizar Paciente");
        gbc.gridx = 1;
        gbc.gridy = 3;
        editPacientePanel.add(atualizarPacienteConfirmButton, gbc);

        atualizarPacienteConfirmButton.addActionListener(e -> {
            String nome = nomePacienteField.getText();
            String sobrenome = sobrenomePacienteField.getText();
            String cpf = cpfPacienteField.getText();
            if (!nome.isEmpty() && !sobrenome.isEmpty() && !cpf.isEmpty()) {
                paciente.setNome(nome);
                paciente.setSobrenome(sobrenome);
                paciente.setCpf(cpf);

                PacienteController controller = new PacienteController();
                controller.atualizar(paciente);
                pacientesPanel.removeAll();
                setPacientes();
                cardLayout.show(cardPanel, "Tabela Paciente");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cardPanel.add(editPacientePanel, "Editar Paciente");
        cardLayout.show(cardPanel, "Editar Paciente");
    }


    private JPanel createDoctorSquare(Profissional profissional, int row) {
        JPanel doctorPanel = new JPanel(new BorderLayout());
        doctorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(profissional.getNomeCompleto());
        JLabel specialtyLabel = new JLabel(new String("          " + profissional.getEspecialidades()));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton editar = new JButton("Editar");
        JButton deletar = new JButton("Deletar");

        buttonPanel.add(editar);
        buttonPanel.add(deletar);

        doctorPanel.add(nameLabel, BorderLayout.WEST);
        doctorPanel.add(specialtyLabel, BorderLayout.CENTER);
        doctorPanel.add(buttonPanel, BorderLayout.EAST);

        deletar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja deletar este médico?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ProfissionalController controller = new ProfissionalController();
                controller.deletar(profissional);
                doctorsPanel.remove(doctorPanel);
                doctorsPanel.revalidate();
                doctorsPanel.repaint();
            }
        });

        editar.addActionListener(e -> {
            showEditDoctorPanel(profissional);
        });

        return doctorPanel;
    }

    private void showEditDoctorPanel(Profissional profissional) {
        if (editDoctorPanel == null) {
            editDoctorPanel = new JPanel(new GridBagLayout());
        } else {
            editDoctorPanel.removeAll();
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomeMedicoLabel = new JLabel("Nome do Médico:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        editDoctorPanel.add(nomeMedicoLabel, gbc);

        JTextField nomeMedicoField = new JTextField(20);
        nomeMedicoField.setText(profissional.getNome());
        gbc.gridx = 1;
        gbc.gridy = 0;
        editDoctorPanel.add(nomeMedicoField, gbc);

        JLabel sobrenomeMedicoLabel = new JLabel("Sobrenome do Médico:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        editDoctorPanel.add(sobrenomeMedicoLabel, gbc);

        JTextField sobrenomeMedicoField = new JTextField(20);
        sobrenomeMedicoField.setText(profissional.getSobrenome());
        gbc.gridx = 1;
        gbc.gridy = 1;
        editDoctorPanel.add(sobrenomeMedicoField, gbc);

        JLabel especialidadeMedicoLabel = new JLabel("Especialidade:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        editDoctorPanel.add(especialidadeMedicoLabel, gbc);

        JTextField especialidadeMedicoField = new JTextField(20);
        especialidadeMedicoField.setText(profissional.getEspecialidades());
        gbc.gridx = 1;
        gbc.gridy = 2;
        editDoctorPanel.add(especialidadeMedicoField, gbc);

        JButton atualizarMedicoConfirmButton = new JButton("Atualizar Profissional");
        gbc.gridx = 1;
        gbc.gridy = 3;
        editDoctorPanel.add(atualizarMedicoConfirmButton, gbc);

        atualizarMedicoConfirmButton.addActionListener(e -> {
            String nome = nomeMedicoField.getText();
            String sobrenome = sobrenomeMedicoField.getText();
            String especialidade = especialidadeMedicoField.getText();
            if (!nome.isEmpty() && !especialidade.isEmpty() && !sobrenome.isEmpty()) {
                profissional.setNome(nome);
                profissional.setSobrenome(sobrenome);
                profissional.setEspecialidades(especialidade);

                ProfissionalController controller = new ProfissionalController();
                controller.atualizar(profissional);
                doctorsPanel.removeAll();
                setDoutores();
                cardLayout.show(cardPanel, "Tabela Médico");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cardPanel.add(editDoctorPanel, "Editar Médico");
        cardLayout.show(cardPanel, "Editar Médico");
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
