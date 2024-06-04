package models;

public class Paciente extends User{
    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;

    public Paciente(String id, String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.id = id;
    }

    public Paciente(){

    }
    public Paciente(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getTipo() {
        return "Paciente";
    }


}




