package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.model.ServiceRecord;
import cz.uhk.fim.servicebookapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    Page<ServiceRecord> findAll(Specification<ServiceRecord> specification, Pageable pageable);
    @Query("SELECT SUM(sr.cost) FROM ServiceRecord sr WHERE sr.car.user=:user")
    Integer getUserTotalCosts(User user);
    @Query("SELECT SUM(sr.cost) FROM ServiceRecord  sr WHERE  sr.car=:car")
    Integer getCarTotalCosts(Car car);
}
