package com.example.tfts3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("redirect:/productos");
        return modelAndView;
    }

    @GetMapping("/Login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("Home/Login");
        return modelAndView;
    }
    // @GetMapping("/")
    // public String indexPage() {
    // return "index";
    // }

    @PostMapping("/send")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {
        String hardcodedValue = "miguelangelcdm@gmail.com"; // Replace with your actual hardcoded value

        if (username.equals(hardcodedValue)) {
            // Perform the necessary actions for successful login

            // Set the input value in session storage if required
            // For example: session.setAttribute("inputValue", username);

            return "redirect:/";
        } else {
            redirectAttributes.addAttribute("error", "Invalid input. Please try again.");
            return "redirect:/Login";
        }
    }
}
