package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public void save(Car car) {
        carRepository.save(car);
    }

    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }
    public List<Car> getUserCars(User user){
        return carRepository.getCarsByUser(user);
    }
}
