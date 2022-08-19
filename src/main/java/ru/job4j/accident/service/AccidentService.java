package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> getAll() {
        return accidentMem.getAll();
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
}
