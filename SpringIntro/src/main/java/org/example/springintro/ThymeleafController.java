package org.example.springintro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymeleafController {

    // Przykładowa metoda, która renderuje widok HTML
//    @GetMapping("/hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "Witaj na stronie Thymeleaf!");
        return "index"; // Oznacza plik "hello.html" w katalogu src/main/resources/templates
    }


}