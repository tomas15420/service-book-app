package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public void save(Car car){
        carRepository.save(car);
    }
}
