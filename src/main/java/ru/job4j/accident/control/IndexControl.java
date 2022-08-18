package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> users = new ArrayList<>();
        users.add("Petr");
        users.add("Ivan");
        users.add("George");
        model.addAttribute("users", users);
        return "index";
    }
}
