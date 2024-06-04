package models;

public class Paciente extends User {
    private String cpf;

    public Paciente(String id, String nome, String sobrenome, String cpf) {
        super(id, nome, sobrenome);
        this.cpf = cpf;
    }
    public Paciente(String nome, String sobrenome, String cpf) {
        super(nome, sobrenome);
        this.cpf = cpf;
    }
    public Paciente() {

    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getTipo() {
        return "Paciente";
    }
}
