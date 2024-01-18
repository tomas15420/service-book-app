package cz.uhk.fim.servicebookapp.controller;

import cz.uhk.fim.servicebookapp.enumeration.ServiceRecordSortField;
import cz.uhk.fim.servicebookapp.exception.UnauthorizedException;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.service.CarService;
import cz.uhk.fim.servicebookapp.service.ServiceRecordService;
import cz.uhk.fim.servicebookapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final UserService userService;
    private final ServiceRecordService serviceRecordService;
    private final CarService carService;
    @GetMapping("/")
    public String dashboard(Model model, Principal principal){
        User loggedUser = userService.getUserByUsername(principal.getName()).orElseThrow(() -> new UnauthorizedException("Nejste přihlášen"));

        Sort sort = Sort.by(Sort.Direction.DESC, ServiceRecordSortField.DATE.getDatabaseFieldName());
        Pageable pageable = PageRequest.of(0,10,sort);

        model.addAttribute("cars", carService.getUserCars(loggedUser));
        model.addAttribute("records", serviceRecordService.findAllByUser(loggedUser,null, null, null, null, pageable));
        model.addAttribute("totalCosts", serviceRecordService.getUserTotalCosts(loggedUser));
        return "index";
    }
}
