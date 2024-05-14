package dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DAO {
    String tabela;


    public DAO(String tabela) {
        this.tabela = tabela;

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
            connection = DriverManager.getConnection(url, "root", "123qwe");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    public void select(ArrayList<String> campos) throws SQLException {
        Connection conn = getConexaoMySQL();

        String camposString = String.join(", ", campos);

        String query = String.format("SELECT %s FROM %s", camposString, this.tabela);

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            for (int i = 1; campos.size() >= i; i++) {

                System.out.printf("%s | ", rs.getObject(i));

            }
            System.out.println();
        }

        rs.close();
        st.close();
        conn.close();
    }
}

