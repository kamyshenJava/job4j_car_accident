package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.Optional;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
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
        return "accident/edit";
    }

    @PostMapping("edit")
    public String replace(@ModelAttribute Accident accident) {
        accidentService.replace(accident);
        return "redirect:/";
    }
}
