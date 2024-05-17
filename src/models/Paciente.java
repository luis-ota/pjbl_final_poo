package models;

public class Paciente {
    public String id;
    public String nome;
    public String sobrenome;
    public String telefone;

    public String nomeCompleto() {
        return nome + " " + sobrenome;
    }
}
