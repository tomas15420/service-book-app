package cz.uhk.fim.servicebookapp.controller;

import cz.uhk.fim.servicebookapp.dto.ServiceAddDto;
import cz.uhk.fim.servicebookapp.enumeration.ServiceRecordSortField;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

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
        if(serviceDto.getOperationId() == null || serviceDto.getOperationId() == 0){
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
            operation = operationService.getOperationById(serviceDto.getOperationId()).orElseThrow(() -> new BadRequestException("Chybný požadavek, zkuste to znovu"));
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

    @GetMapping("/service-records/{serviceId}/edit")
    public String editServiceForm(@PathVariable Long serviceId, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        ServiceRecord serviceRecord = serviceRecordService.getServiceRecordById(serviceId).orElseThrow(() -> new BadRequestException("Servisní záznam s tímto ID neexistuje"));

        Car car = serviceRecord.getCar();
        if(!car.getUser().equals(loggedUser)) throw new ForbiddenException("Nemáte oprávnění k úpravě tohoto záznamu");

        ServiceAddDto serviceDto = modelMapper.map(serviceRecord, ServiceAddDto.class);
        serviceDto.setOperationId(serviceRecord.getOperation().getId());

        model.addAttribute("operations", operationService.getUserOperations(loggedUser));
        model.addAttribute("car", car);
        model.addAttribute("service", serviceDto);
        return "service/edit-service";
    }

    @PostMapping("/service-records/{serviceId}/edit")
    public String editService(@PathVariable Long serviceId, @ModelAttribute("service") @Valid ServiceAddDto serviceDto, BindingResult bindingResult, Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        ServiceRecord serviceRecord = serviceRecordService.getServiceRecordById(serviceId).orElseThrow(() -> new BadRequestException("Servisní záznam s tímto ID neexistuje"));

        Car car = serviceRecord.getCar();
        if(!car.getUser().equals(loggedUser)) throw new ForbiddenException("Nemáte oprávnění k přidání záznamu k tomuto vozidlu");

        Operation operation = null;
        if(serviceDto.getOperationId() == null || serviceDto.getOperationId() == 0){
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
            operation = operationService.getOperationById(serviceDto.getOperationId()).orElseThrow(() -> new BadRequestException("Chybný požadavek, zkuste to znovu"));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("operations", operationService.getUserOperations(loggedUser));
            model.addAttribute("car", car);
            return "service/edit-service";
        }

        serviceRecord = modelMapper.map(serviceDto, ServiceRecord.class);
        serviceRecord.setId(serviceId);
        serviceRecord.setOperation(operation);
        serviceRecord.setCar(car);

        System.out.println(serviceRecord);

        serviceRecordService.save(serviceRecord);

        return "redirect:/service-records/?success=edit";
    }

    @GetMapping("/service-records/{serviceId}/delete")
    public String deleteService(@PathVariable Long serviceId, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));
        ServiceRecord serviceRecord = serviceRecordService.getServiceRecordById(serviceId).orElseThrow(() -> new BadRequestException("Servisní záznam s tímto ID neexistuje"));
        if(!serviceRecord.getCar().getUser().equals(loggedUser)) throw new ForbiddenException("Nemůžete smazat cizí záznam");

        serviceRecordService.delete(serviceRecord);
        return "redirect:/service-records/?success=delete";
    }

    @GetMapping("/service-records")
    public String serviceRecordsPage(Model model, Principal principal
            , @RequestParam(required = false) Long carId
            , @RequestParam(required = false) Long operationId
            , @RequestParam(required = false) LocalDate startDate
            , @RequestParam(required = false) LocalDate endDate
            , @RequestParam(defaultValue = "DATE") ServiceRecordSortField field
            , @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection
            , @RequestParam(defaultValue = "1") Integer page){

        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));

        Sort sort = Sort.by(sortDirection, field.getDatabaseFieldName());
        Pageable pageable = PageRequest.of(page-1,2, sort);

        Page<ServiceRecord> records = serviceRecordService.findAllByUser(loggedUser,carId, operationId, startDate, endDate, pageable);

        model.addAttribute("records", records);
        model.addAttribute("cars", carService.getUserCars(loggedUser));
        model.addAttribute("operations", operationService.getUserOperations(loggedUser));
        return "/service/records";
    }
}
