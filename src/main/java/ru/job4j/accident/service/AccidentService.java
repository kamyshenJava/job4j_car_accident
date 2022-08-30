package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RulesRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentTypeRepository;
    private RulesRepository rulesRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository,
                           RulesRepository rulesRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.rulesRepository = rulesRepository;
    }

    public List<Accident> getAll() {
        return (List<Accident>) accidentRepository.findAll();
    }

    public List<AccidentType> getTypes() {
        return (List<AccidentType>) accidentTypeRepository.findAll();
    }

    public List<Rule> getRules() {
        return (List<Rule>) rulesRepository.findAll();
    }

    public void createOrUpdate(Accident accident) {
        accidentRepository.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public void setTypeAndRules(Accident accident, String[] ids, String typeId) {
        Set<Rule> rules = Arrays.stream(ids)
                .map(id -> rulesRepository.findById(Integer.parseInt(id)).get())
                .collect(Collectors.toSet());
        accident.setRules(rules);
        accident.setType(accidentTypeRepository.findById((Integer.parseInt(typeId))).get());
    }
}
