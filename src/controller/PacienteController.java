package controller;

import dao.DAO;
import models.Paciente;
import utils.ConvertMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class PacienteController {
    private DAO dao = new DAO("Pacientes");

    public ArrayList<Paciente> listar() {
        ArrayList<String> campos = new ArrayList<>();
        campos.add("Id");
        campos.add("Nome");
        campos.add("Sobrenome");
        campos.add("Cpf");

        try {
            ArrayList<Paciente> result = new ArrayList<>();
            ArrayList<Map<String, String>> resultFromDao = dao.select(campos);

            for (Map<String, String> row : resultFromDao) {
                Paciente p = ConvertMap.mapToObject(row, Paciente.class);
                result.add(p);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionar(Paciente novoPaciente) {
        try {
            dao.insert(ConvertMap.objectToMap(novoPaciente));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Paciente paciente) {
        try {
            dao.update(ConvertMap.objectToMap(paciente), paciente.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Paciente paciente) {
        try {
            dao.delet(paciente.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
