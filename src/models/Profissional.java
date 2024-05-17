package models;

public class Profissional {
    public String id;
    public String nome;
    public String sobrenome;
    public String especialidades;

    public Profissional(String nome, String especialidade, String sobrenome) {
        this.nome = nome;
        this.especialidades = especialidade;
        this.sobrenome = sobrenome;
    }

    public Profissional() {
    }

    public String nomeCompleto() {
        return nome + " " + sobrenome;
    }
}
