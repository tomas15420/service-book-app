package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {
}
