package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @EntityGraph(value = "accident-rules", type = EntityGraph.EntityGraphType.LOAD)
    Iterable<Accident> findAll();

    @EntityGraph(value = "accident-rules", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Accident> findById(int id);
}
