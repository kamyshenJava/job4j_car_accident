package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        addAccidents();
    }

    private void addAccidents() {
        Accident accident1 = Accident.of("some name1", "some text1", "some address1");
        Accident accident2 = Accident.of("some name2", "some text2", "some address2");
        Accident accident3 = Accident.of("some name3", "some text3", "some address3");
        accidents.put(1, accident1);
        accidents.put(2, accident2);
        accidents.put(3, accident3);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidents.values());
    }
}
