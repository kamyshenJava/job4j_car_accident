package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Entity
@NamedEntityGraph(
        name = "accident-rules",
        attributeNodes = {
             @NamedAttributeNode("rules")
        }
)
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne(cascade = CascadeType.MERGE)
    private AccidentType type;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "accidents_rules",
            joinColumns = { @JoinColumn(name = "accident_id")},
            inverseJoinColumns = { @JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules;

    public static Accident of(String name, String text, String address, AccidentType type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        accident.rules = rules;
        return accident;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id && Objects.equals(name, accident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
