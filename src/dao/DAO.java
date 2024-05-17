package dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Map;

public class DAO {
    String tabela;

    Connection connection;


    public DAO(String tabela) {
        this.tabela = tabela;
        this.getConexaoMySQL();

    }

    public void getConexaoMySQL() {
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

        this.connection = connection;
    }
    public ArrayList<Map<String, String>> select(ArrayList<String> campos) throws SQLException {
        Connection conn = this.connection;

        String camposString = String.join(", ", campos);

        String query = String.format("SELECT %s FROM %s", camposString, this.tabela);

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);

        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();

        while (rs.next()) {
            Map<String, String> currentResult = new HashMap<String, String>();
            for (int i = 1; campos.size() >= i; i++) {
                currentResult.put(campos.get(i-1), rs.getString(i));
            }
            result.add(currentResult);
        }

        rs.close();
        st.close();
        conn.close();
    }

    public void insert(HashMap<String, String> dados) throws SQLException {
        this.getConexaoMySQL();
        Connection conn = this.connection;

        ArrayList<String> camposS = new ArrayList<>();

        for (Map.Entry<String, String> entry : dados.entrySet()) {
            camposS.add(String.format("%s", entry.getKey()));
        }

        String camposSFormated = String.join(", ", camposS);

        String query = "INSERT INTO " + this.tabela;
        query += String.format(" (Id, %s) VALUES (", camposSFormated);

        ArrayList<String> camposX = new ArrayList<>();
        camposX.add("UUID()");
        for (Map.Entry<String, String> entry : dados.entrySet()) {
            camposX.add(String.format("'%s'", entry.getValue()));
        }
        String camposSFormatedX = String.join(", ", camposX);

        query+=camposSFormatedX;
        query+=");";


        Statement st = conn.createStatement();

        st.executeUpdate(query);

        st.close();
        conn.close();

        return result;
    }
}

