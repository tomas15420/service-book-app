package cz.uhk.fim.servicebookapp.controller;

import cz.uhk.fim.servicebookapp.dto.ServiceAddDto;
import cz.uhk.fim.servicebookapp.exception.BadRequestException;
import cz.uhk.fim.servicebookapp.exception.ForbiddenException;
import cz.uhk.fim.servicebookapp.exception.UnauthorizedException;
import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.model.Operation;
import cz.uhk.fim.servicebookapp.model.ServiceRecord;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.service.CarService;
import cz.uhk.fim.servicebookapp.service.OperationService;
import cz.uhk.fim.servicebookapp.service.ServiceRecordService;
import cz.uhk.fim.servicebookapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ServiceController {
    private final OperationService operationService;
    private final UserService userService;
    private final CarService carService;
    private final ServiceRecordService serviceRecordService;
    private final ModelMapper modelMapper;
    @GetMapping("/cars/{carId}/service-records/add")
    public String addServiceForm(@PathVariable Long carId, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        Car car = carService.getCarById(carId).orElseThrow(() -> new BadRequestException("Vozidlo s tímto ID neexistuje"));
        if(!car.getUser().equals(loggedUser)) throw new ForbiddenException("Nemáte oprávnění k přidání záznamu k tomuto vozidlu");

        model.addAttribute("operations", operationService.getUserOperations(loggedUser));
        model.addAttribute("car", car);
        model.addAttribute("service", new ServiceAddDto());
        return "service/add-service";
    }

    @PostMapping("/cars/{carId}/service-records/add")
    public String addService(@PathVariable Long carId, @ModelAttribute("service") @Valid ServiceAddDto serviceDto, BindingResult bindingResult, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        Car car = carService.getCarById(carId).orElseThrow(() -> new BadRequestException("Vozidlo s tímto ID neexistuje"));
        if(!car.getUser().equals(loggedUser)) throw new ForbiddenException("Nemáte oprávnění k přidání záznamu k tomuto vozidlu");

        Operation operation = null;
        if(serviceDto.getOperation() == null || serviceDto.getOperation() == 0){
            if(serviceDto.getNewOperation().isBlank()){
                bindingResult.rejectValue("newOperation",null,"Vytvořte nebo vyberte úkon pro vozidlo");
            }else{
                operation = operationService.getOperationByName(serviceDto.getNewOperation())
                        .orElseGet(() -> {
                            Operation newOperation = Operation.builder().user(loggedUser).name(serviceDto.getNewOperation()).build();
                            operationService.save(newOperation);
                            return newOperation;
                        });
            }
        }
        else{
            operation = operationService.getOperationById(serviceDto.getOperation()).orElseThrow(() -> new BadRequestException("Chybný požadavek, zkuste to znovu"));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("operations", operationService.getUserOperations(loggedUser));
            model.addAttribute("car", car);
            return "service/add-service";
        }

        ServiceRecord serviceRecord = modelMapper.map(serviceDto, ServiceRecord.class);
        serviceRecord.setOperation(operation);
        serviceRecord.setCar(car);
        serviceRecordService.save(serviceRecord);

        return "redirect:/cars/"+carId+"?success=add_service";
    }
}
