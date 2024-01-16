package cz.uhk.fim.servicebookapp.controller;

import cz.uhk.fim.servicebookapp.dto.UserRegisterDto;
import cz.uhk.fim.servicebookapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new UserRegisterDto());
        return "register/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserRegisterDto userDto, BindingResult bindingResult){

        if(userService.getUserByEmail(userDto.getEmail()).isPresent()){
            bindingResult.rejectValue("email",null,"Účet s tímto E-mailem již existuje");
        }

        if(userDto.getPassword() != null && !userDto.getPassword2().equals(userDto.getPassword())){
            bindingResult.rejectValue("password2", null, "Hesla se neshodují");
        }

        if(bindingResult.hasErrors()){
            return "register/register";
        }

        userService.registerUser(userDto);

        return "redirect:/login";
    }
}
