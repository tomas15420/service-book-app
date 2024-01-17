package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> getCarsByUser(User user);
}
