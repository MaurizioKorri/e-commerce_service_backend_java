/*package com.example.demo.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    @GetMapping(value = {"/welcome", "/"})
    public String greeting(@RequestParam(name = "nome", required = true, defaultValue = "World") String name, Model model){
                // quindi name è una variabile che sara disponibile dentro al metodo, che viene presa dall url
                //che sarebbe localhost:8080/welcome?nome=qualcosa
        model.addAttribute("userToGreet", name);
        return "greeting";//ritorna il nome di un template greeting.html alla directory /welcome
        //dobbiamo poi mandarlo alla vista, e per questo ci sono dei model
    }

}*/
