package dao;

import dao.AbstractDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAO extends AbstractDAO {
    public DAO(String tabela) {
        super(tabela);
    }

    @Override
    public ArrayList<Map<String, String>> select(ArrayList<String> campos) throws DAOException {
        try {
            this.getConexaoMySQL();
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

            return result;
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar a consulta SELECT", e);
        }
    }

    @Override
    public void insert(Map<String, String> dados) throws DAOException {
        try {
            this.getConexaoMySQL();
            Connection conn = this.connection;
            ArrayList<String> camposS = new ArrayList<>();

            for (Map.Entry<String, String> entry : dados.entrySet()) {
                camposS.add(String.format("%s", entry.getKey()));
            }

            String camposSFormated = String.join(", ", camposS);
            String query = "INSERT INTO " + this.tabela + String.format(" (id, %s) VALUES (", camposSFormated);

            ArrayList<String> camposX = new ArrayList<>();
            camposX.add("UUID()");
            for (Map.Entry<String, String> entry : dados.entrySet()) {
                camposX.add(String.format("'%s'", entry.getValue()));
            }
            String camposSFormatedX = String.join(", ", camposX);

            query += camposSFormatedX + ");";
            Statement st = conn.createStatement();
            st.executeUpdate(query);

            st.close();
            conn.close();
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar a inserção", e);
        }
    }

    @Override
    public void update(Map<String, String> dados, String id) throws DAOException {
        try {
            this.getConexaoMySQL();
            Connection conn = this.connection;
            ArrayList<String> camposS = new ArrayList<>();

            for (Map.Entry<String, String> entry : dados.entrySet()) {
                camposS.add(String.format("\n  %s = '%s'", entry.getKey(), entry.getValue()));
            }

            String camposSFormated = String.join(", ", camposS);
            String query =  "UPDATE " + this.tabela + " \nSET %s\nWHERE id = '%s'";
            query = String.format(query, camposSFormated, id);

            Statement st = conn.createStatement();
            st.executeUpdate(query);

            st.close();
            conn.close();
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar a atualização", e);
        }
    }

    @Override
    public void delet(String id) throws DAOException {
        try {
            this.getConexaoMySQL();
            Connection conn = this.connection;

            String query =  String.format("DELETE FROM %s WHERE id = '%s'",this.tabela, id);

            Statement st = conn.createStatement();
            st.executeUpdate(query);

            st.close();
            conn.close();
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar a exclusão", e);
        }
    }
}
