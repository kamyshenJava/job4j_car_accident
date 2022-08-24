package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    private static final String SELECT_ALL_ACCIDENTS
            = "select a.*, t.name type_name from accidents a left join types t on a.type_id = t.id";

    private static final String SELECT_RULES_FOR_ACCIDENT
            = "select r.* from rules r join accidents_rules ar on ar.rule_id = r.id where ar.accident_id = ?";

    private static final String INSERT_INTO_ACCIDENTS
            = "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)";

    private static final String INSERT_INTO_ACCIDENTS_RULES
            = "insert into accidents_rules (accident_id, rule_id) values (?, ?)";

    private static final String SELECT_ALL_TYPES = "select * from types";
    private static final String SELECT_ALL_RULES = "select * from rules";
    private static final String SELECT_ACCIDENT_BY_ID
            = "select a.*, t.name type_name from accidents a left join types t on a.type_id = t.id where a.id = ?";
    private static final String SELECT_TYPE_BY_ID = "select * from types where id = ?";
    private static final String SELECT_RULE_BY_ID = "select * from rules where id = ?";
    private static final String UPDATE_ACCIDENT
            = "update accidents set name = ?, text = ?, address = ?, type_id = ? where id = ?";
    private static final String DELETE_RULES = "delete from accidents_rules where accident_id = ?";

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void create(Accident accident) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int id;
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_INTO_ACCIDENTS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKeys().size() > 1) {
            id = (int) keyHolder.getKeys().get("id");
        } else {
            id = keyHolder.getKey().intValue();
        }
        accident.getRules().forEach(rule -> jdbc.update(INSERT_INTO_ACCIDENTS_RULES, id, rule.getId()));

    }

    public List<Accident> getAll() {
        return jdbc.query(SELECT_ALL_ACCIDENTS, this::makeAccidentFromResultSet);
    }

    public List<AccidentType> getTypes() {
        return jdbc.query(SELECT_ALL_TYPES, ((rs, rowNum) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
        }));
    }

    public List<Rule> getRules() {
        return jdbc.query(SELECT_ALL_RULES, ((rs, rowNum) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
        }));
    }

    public Optional<Accident> findById(int id) {
        Accident rsl = jdbc.queryForObject(SELECT_ACCIDENT_BY_ID, this::makeAccidentFromResultSet, id);
        return Optional.ofNullable(rsl);
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject(SELECT_TYPE_BY_ID, (rs, row) ->
                AccidentType.of(rs.getInt("id"), rs.getString("name")), id);
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject(SELECT_RULE_BY_ID, (rs, row) ->
                Rule.of(rs.getInt("id"), rs.getString("name")), id);
    }

    public void replace(Accident accident) {
        jdbc.update(UPDATE_ACCIDENT, accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
        jdbc.update(DELETE_RULES, accident.getId());
        accident.getRules().forEach(rule -> jdbc.update(INSERT_INTO_ACCIDENTS_RULES, accident.getId(), rule.getId()));
    }

    private Accident makeAccidentFromResultSet(ResultSet rs, int row) throws SQLException {
        Accident accident = new Accident();
        AccidentType type = new AccidentType();
        int accidentId = rs.getInt("id");
        accident.setId(accidentId);
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        type.setId(rs.getInt("type_id"));
        type.setName((rs.getString("type_name")));
        accident.setType(type);
        Set<Rule> rules = findRulesForAccident(accidentId);
        accident.setRules(rules);
        return accident;
    }

    private Set<Rule> findRulesForAccident(int accidentId) {
        return new HashSet<>(jdbc.query(SELECT_RULES_FOR_ACCIDENT, (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, accidentId
        ));
    }
}
