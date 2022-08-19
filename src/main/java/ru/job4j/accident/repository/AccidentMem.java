package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    private AtomicInteger ai = new AtomicInteger(1);

    public AccidentMem() {
        addAccidents();
    }

    private void addAccidents() {
        Accident accident1 = Accident.of("some name1", "some text1", "some address1");
        Accident accident2 = Accident.of("some name2", "some text2", "some address2");
        Accident accident3 = Accident.of("some name3", "some text3", "some address3");
        create(accident1);
        create(accident2);
        create(accident3);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidents.values());
    }

    public void create(Accident accident) {
        int id = ai.getAndIncrement();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public void replace(Accident accident) {
        Accident oldAc = accidents.get(accident.getId());
        oldAc.setName(accident.getName());
        oldAc.setText(accident.getText());
        oldAc.setAddress(accident.getAddress());
    }
}
