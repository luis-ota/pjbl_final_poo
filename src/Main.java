import dao.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO("Pacientes");

        ArrayList<String> campos = new ArrayList<>();
        campos.add("Id");
        campos.add("Nome");
        campos.add("Sobrenome");

        dao.select(campos);

        HashMap<String, String> dados = new HashMap<>();
        dados.put("Nome", "John");
        dados.put("Sobrenome", "Doe");
        dados.put("Cpf", "123.456.789-00");
        dados.put("Email", "john.doe@example.com");
        dados.put("Telefone", "(123) 456-7890");
        dados.put("DataNascimento", "1990-01-01");
        dados.put("Genero", "Masculino");
        dados.put("EnderecoId", "1");


        dao.insert(dados);


    }
}

