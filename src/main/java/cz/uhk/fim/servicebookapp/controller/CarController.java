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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/cars/add-car")
    public String addCarForm(Model model){
        model.addAttribute("car",new Car());
        model.addAttribute("brands", carBrandService.getCarBrands());
        return "car/add-car";
    }

    @PostMapping("/cars/add-car")
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

    @GetMapping("/cars/{carId}/edit")
    public String editCarForm(Model model, @PathVariable Long carId, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Nejste přihlášen"));
        Car car = carService.getCarById(carId).orElseThrow(() -> new RuntimeException("Vozidlo s tímto ID neexistuje"));
        if(!car.getUser().equals(loggedUser)) return "redirect:/cars";
        model.addAttribute("car", car);
        model.addAttribute("brands", carBrandService.getCarBrands());
        return "/car/edit-car";
    }
    @PostMapping("/cars/{carId}/edit")
    public String editCar(@PathVariable Long carId, @ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Nejste přihlášen"));
        Car foundCar = carService.getCarById(carId).orElseThrow(() -> new RuntimeException("Vozidlo s tímto ID neexistuje"));
        if(!foundCar.getUser().equals(loggedUser)) throw new RuntimeException("Neoprávněná úprava vozidla");
        car.setId(foundCar.getId());
        car.setUser(foundCar.getUser());

        if(bindingResult.hasErrors()){
            model.addAttribute("brands",carBrandService.getCarBrands());
            return "/car/add-car";
        }
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars")
    public String carsList(Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Nejste přihlášen"));
        model.addAttribute("cars", carService.getUserCars(loggedUser));
        return "car/car-list";
    }
}
