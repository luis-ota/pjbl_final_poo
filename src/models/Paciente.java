package models;

public class Paciente {
    private String id;
    private String nome;
    private String sobrenome;
    private String telefone;

    private String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getId() {
        return this.id;
    }


}




