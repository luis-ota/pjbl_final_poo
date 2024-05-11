package dao;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DAO {
    String tabela;
    List<String> campos;
    Object dto;

    public DAO(String tabela, List<String> campos, Object dto) {
        this.tabela = tabela;
        this.campos = campos;
        this.dto = dto;

    }

    public static java.sql.Connection getConexaoMySQL() {
        // Atributo do tipo Connection
        Connection connection = null;

        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/medicaly";

        try {
            connection = DriverManager.getConnection(url, "root", "PUC@1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    public void select() throws SQLException {
        // Obtém a conexão com o banco de dados
        Connection conn = getConexaoMySQL();

        // Monta a query SQL para selecionar todos os registros da tabela
        String query = "SELECT * FROM " + this.tabela;

        // Cria um objeto Statement para executar a query
        Statement st = conn.createStatement();

        // Executa a query e obtém um ResultSet com os resultados
        ResultSet rs = st.executeQuery(query);

        // Itera sobre os resultados do ResultSet
        while (rs.next()) {
            System.out.println(rs.getObject(2));
        }

        // Fecha o ResultSet, o Statement e a conexão com o banco de dados
        rs.close();
        st.close();
        conn.close();
    }
}

