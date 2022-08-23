package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        addTypesAndRulesToModel(model);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rule.ids");
        String typeId = req.getParameter("type.id");
        accidentService.setTypeAndRules(accident, ids, typeId);
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        Optional<Accident> accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("accident", accident.get());
        addTypesAndRulesToModel(model);
        return "accident/edit";
    }

    @PostMapping("edit")
    public String replace(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rule.ids");
        String typeId = req.getParameter("type.id");
        accidentService.setTypeAndRules(accident, ids, typeId);
        accidentService.replace(accident);
        return "redirect:/";
    }

    private void addTypesAndRulesToModel(Model model) {
        List<AccidentType> types = accidentService.getTypes();
        List<Rule> rules = accidentService.getRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
    }
}
