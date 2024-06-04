package models;

public abstract class User {
    private String id;
    private String nome;
    private String sobrenome;

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }


    public String getId() {
        return this.id;
    }

    // Adicionando o método abstrato
    public abstract String getTipo();
}
