package controller;

import dao.DAO;
import models.Paciente;
import models.Profissional;
import utils.ConvertMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ProfissionalController {
    private DAO dao = new DAO("Profissionais");

    public ArrayList<Profissional> listar() {
        ArrayList<String> campos = new ArrayList<String>();
        campos.add("Id");
        campos.add("Nome");
        campos.add("Sobrenome");
        campos.add("Especialidades");

        try {
            ArrayList<Profissional> result = new ArrayList<Profissional>();
            ArrayList<Map<String, String>> resultFromDao = dao.select(campos);
            for (Map<String, String> row : resultFromDao) {
                Profissional p = ConvertMap.mapToObject(row, Profissional.class);
                result.add(p);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Profissional>();
        }
    }

    public void adicionar(Profissional novoMedico) {
        try {
            dao.insert(ConvertMap.objectToMap(novoMedico));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Profissional Medico) {
        try {
            dao.update(ConvertMap.objectToMap(Medico), Medico.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
