package wangdinghua17204222.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import wangdinghua17204222.entity.User;

@Controller
public class HomeController{
    @PostMapping("/login")
    public String check(@Validated User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute(user);
            return "login";
        } else {
            return "redirect:home";
        }
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(new User());
        return "welcome";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute(new User());
        return "register";
    }
}
