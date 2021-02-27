package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String tellHelloToUser(@RequestParam("name") String name,
                                  @RequestParam(value = "surname", required = false) String surname,
                                  Model model) {
        System.out.printf("Hello name: %s, surname %s", name, surname);
        model.addAttribute("message", String.format("Hello name: %s, surname %s", name, surname));
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String tellGoodByeToUser() {
        return "first/goodBye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                                     @RequestParam("b") int b,
                                     @RequestParam("action") String action,
                                     Model calcModel) {
        calcModel.addAttribute("answer", performCalculation(a, b, action));

        return "first/calculator";
    }

    private double performCalculation(int a, int b, String action) {
        double answer = 0;
        switch (action) {
            case "multiplication":
                answer = a * b;
                break;
            case "addition":
                answer = a + b;
                break;
            case "subtraction":
                answer = a - b;
                break;
            case "division":
                answer = a / (double)b;
                break;
            default:
                break;
        }
        return answer;
    }
}
