package models;

public class Profissional extends User{
    private String nome;
    private String sobrenome;
    private String especialidades;
    private String id;

    public Profissional(String nome, String especialidade, String sobrenome) {
        this.nome = nome;
        this.especialidades = especialidade;
        this.sobrenome = sobrenome;
    }

    public Profissional() {
    }

    public Profissional(String nome, String especialidade, String sobrenome, String id) {
        this.nome = nome;
        this.especialidades = especialidade;
        this.sobrenome = sobrenome;
        this.id = id;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public String getNome(){
        return nome;
    }

    public String getSobrenome(){
        return sobrenome;
    }


    public String getEspecialidades(){
        return especialidades;
    }

    public String getId(){
        return id;
    }

    @Override
    public String getTipo() {
        return "Profissional";
    }


}
