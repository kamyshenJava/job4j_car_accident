package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private AccidentJdbcTemplate accidentStore;

    public AccidentService(AccidentJdbcTemplate accidentStore) {
        this.accidentStore = accidentStore;
    }

    public List<Accident> getAll() {
        return accidentStore.getAll();
    }

    public List<AccidentType> getTypes() {
        return accidentStore.getTypes();
    }

    public List<Rule> getRules() {
        return accidentStore.getRules();
    }

    public AccidentType findTypeById(int id) {
        return accidentStore.findTypeById(id);
    }

    public Rule findRuleById(int id) {
        return accidentStore.findRuleById(id);
    }

    public void create(Accident accident) {
        accidentStore.create(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentStore.findById(id);
    }

    public void replace(Accident accident) {
        accidentStore.replace(accident);
    }

    public void setTypeAndRules(Accident accident, String[] ids, String typeId) {
        Set<Rule> rules = Arrays.stream(ids)
                .map(id -> accidentStore.findRuleById(Integer.parseInt(id)))
                .collect(Collectors.toSet());
        accident.setRules(rules);
        accident.setType(accidentStore.findTypeById((Integer.parseInt(typeId))));
    }
}
