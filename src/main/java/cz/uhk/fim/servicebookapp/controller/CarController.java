package cz.uhk.fim.servicebookapp.controller;

import cz.uhk.fim.servicebookapp.exception.BadRequestException;
import cz.uhk.fim.servicebookapp.exception.ForbiddenException;
import cz.uhk.fim.servicebookapp.exception.UnauthorizedException;
import cz.uhk.fim.servicebookapp.model.Car;
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
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
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
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        Car car = carService.getCarById(carId).orElseThrow(() -> new BadRequestException("Toto ID vozidla neexistuje"));
        if(!car.getUser().equals(loggedUser)) throw new ForbiddenException("Neoprávněný přístup");
        model.addAttribute("car", car);
        model.addAttribute("brands", carBrandService.getCarBrands());
        return "/car/edit-car";
    }
    @PostMapping("/cars/{carId}/edit")
    public String editCar(@PathVariable Long carId, @ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        Car foundCar = carService.getCarById(carId).orElseThrow(() -> new BadRequestException("Vozidlo s tímto ID neexistuje"));
        if(!foundCar.getUser().equals(loggedUser)) throw new ForbiddenException("Neoprávněná úprava vozidla");
        car.setId(foundCar.getId());
        car.setUser(foundCar.getUser());

        if(bindingResult.hasErrors()){
            model.addAttribute("brands",carBrandService.getCarBrands());
            return "/car/edit-car";
        }
        carService.save(car);
        return "redirect:/cars?success=add";
    }

    @GetMapping("/cars/{carId}/delete")
    public String deleteCar(@PathVariable Long carId, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        Car car = carService.getCarById(carId).orElseThrow(() -> new BadRequestException("Vozidlo s tímto ID neexistuje"));
        if(!car.getUser().equals(loggedUser)) throw new ForbiddenException("Neoprávněný přístup");

        carService.delete(car);
        return "redirect:/cars?success=delete";
    }

    @GetMapping("/cars")
    public String carsList(Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        model.addAttribute("cars", carService.getUserCars(loggedUser));
        return "car/car-list";
    }
}
