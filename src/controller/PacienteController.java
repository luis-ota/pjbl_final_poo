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
        ArrayList<String> campos = new ArrayList<String>();
        campos.add("Id");
        campos.add("Nome");
        campos.add("Sobrenome");
        campos.add("Telefone");

        try {
            ArrayList<Paciente> result = new ArrayList<Paciente>();
            ArrayList<Map<String, String>> resultFromDao = dao.select(campos);
            for (Map<String, String> row : resultFromDao) {
                Paciente p = ConvertMap.mapToObject(row, Paciente.class);
                result.add(p);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Paciente>();
        }
    }
}
