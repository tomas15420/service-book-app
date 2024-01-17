package cz.uhk.fim.servicebookapp.controller;

import cz.uhk.fim.servicebookapp.dto.CarAddDto;
import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.model.CarBrand;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.service.CarBrandService;
import cz.uhk.fim.servicebookapp.service.CarService;
import cz.uhk.fim.servicebookapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarBrandService carBrandService;
    private final CarService carService;
    private final UserService userService;

    @GetMapping("add-car")
    public String addCarForm(Model model){
        model.addAttribute("car",new Car());
        model.addAttribute("brands", carBrandService.getCarBrands());
        return "car/add-car";
    }

    @PostMapping("add-car")
    public String addCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Nejste přihlášen"));
        if(bindingResult.hasErrors()){
            model.addAttribute("brands",carBrandService.getCarBrands());
            return "/car/add-car";
        }
        car.setUser(loggedUser);
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("cars")
    public String carsList(Model model){
        return "car/car-list";
    }
}
