package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.model.CarBrand;
import cz.uhk.fim.servicebookapp.repository.CarBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarBrandService {
    private final CarBrandRepository carBrandRepository;

    public List<CarBrand> getCarBrands(){
        return carBrandRepository.findAll();
    }
}
