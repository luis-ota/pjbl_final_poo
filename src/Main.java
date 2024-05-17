import controller.PacienteController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PacienteController controller = new PacienteController();
        controller.listar();
    }
}

