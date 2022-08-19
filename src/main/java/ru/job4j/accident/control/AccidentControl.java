package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        addTypesToModel(model);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(accidentService.findTypeById((Integer.parseInt(req.getParameter("type.id")))));
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        Optional<Accident> accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            return "index";
        }
        model.addAttribute("accident", accident.get());
        addTypesToModel(model);
        return "accident/edit";
    }

    @PostMapping("edit")
    public String replace(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(accidentService.findTypeById((Integer.parseInt(req.getParameter("type.id")))));
        accidentService.replace(accident);
        return "redirect:/";
    }

    private void addTypesToModel(Model model) {
        List<AccidentType> types = accidentService.getTypes();
        model.addAttribute("types", types);
    }
}
