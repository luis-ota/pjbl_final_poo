package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDAO {
    String tabela;
    Connection connection;

    public AbstractDAO(String tabela) {
        this.tabela = tabela;
        this.getConexaoMySQL();
    }

    public abstract ArrayList<Map<String, String>> select(ArrayList<String> campos) throws SQLException, DAOException;
    public abstract void insert(Map<String, String> dados) throws SQLException, DAOException;
    public abstract void update(Map<String, String> dados, String id) throws SQLException, DAOException;
    public abstract void delet(String id) throws SQLException, DAOException;

    public void getConexaoMySQL() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/medicaly";

        try {
            connection = DriverManager.getConnection(url, "root", "luis");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
