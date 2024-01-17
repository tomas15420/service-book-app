package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
