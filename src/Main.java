import dao.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO("Pacientes");

        ArrayList<String> campos = new ArrayList<String>();
        campos.add("Id");
        campos.add("Nome");
        campos.add("Sobrenome");

        dao.select(campos);

    }
}

