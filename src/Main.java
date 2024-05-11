import dao.DAO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        List<String> campos = Arrays.asList("Id", "Nome", "Sobrenome", "Cpf", "Email", "Telefone", "DataNascimento", "Genero", "EnderecoId");
        DAO dao = new DAO("pacientes", campos, "");
        dao.select();

    }
}

