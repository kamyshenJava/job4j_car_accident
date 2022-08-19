package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    private AtomicInteger ai = new AtomicInteger(1);

    private List<AccidentType> types = new ArrayList<>();

    public AccidentMem() {
        addTypes();
        addAccidents();
    }

    private void addAccidents() {
        Accident accident1 = Accident.of("some name1", "some text1", "some address1", types.get(1));
        Accident accident2 = Accident.of("some name2", "some text2", "some address2", types.get(2));
        Accident accident3 = Accident.of("some name3", "some text3", "some address3", types.get(3));
        create(accident1);
        create(accident2);
        create(accident3);
    }

    private void addTypes() {
        types.add(AccidentType.of(0, "two cars"));
        types.add(AccidentType.of(1, "a car and a truck"));
        types.add(AccidentType.of(2, "two trucks"));
        types.add(AccidentType.of(3, "a car and a pedestrian"));
        types.add(AccidentType.of(4, "a car and a bicycle"));
        types.add(AccidentType.of(5, "other"));
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidents.values());
    }

    public List<AccidentType> getTypes() {
        return types;
    }

    public void create(Accident accident) {
        int id = ai.getAndIncrement();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    public void replace(Accident accident) {
        Accident oldAc = accidents.get(accident.getId());
        oldAc.setName(accident.getName());
        oldAc.setText(accident.getText());
        oldAc.setAddress(accident.getAddress());
        oldAc.setType(accident.getType());
    }
}
