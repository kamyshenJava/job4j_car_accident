package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> getAll() {
        return accidentMem.getAll();
    }

    public List<AccidentType> getTypes() {
        return accidentMem.getTypes();
    }

    public List<Rule> getRules() {
        return accidentMem.getRules();
    }

    public AccidentType findTypeById(int id) {
        return accidentMem.findTypeById(id);
    }

    public Rule findRuleById(int id) {
        return accidentMem.findRuleById(id);
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public void replace(Accident accident) {
        accidentMem.replace(accident);
    }

    public void setTypeAndRules(Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rule.ids");
        Set<Rule> rules = Arrays.stream(ids)
                .map(id -> accidentMem.findRuleById(Integer.parseInt(id)))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        accident.setType(accidentMem.findTypeById((Integer.parseInt(req.getParameter("type.id")))));
    }
}
