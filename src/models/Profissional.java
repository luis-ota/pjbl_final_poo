package models;

public class Profissional extends User {
    private String especialidade;

    public Profissional(String id, String nome, String sobrenome, String especialidade) {
        super(id, nome, sobrenome);
        this.especialidade = especialidade;
    }
    public Profissional(String nome, String sobrenome, String especialidade) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.especialidade = especialidade;
    }

    public Profissional() {
    }

    public void setEspecialidades(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String getTipo() {
        return "Profissional";
    }

    public String getEspecialidades() {
        return this.especialidade;
    }
}
