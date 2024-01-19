package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.model.Car;
import cz.uhk.fim.servicebookapp.model.ServiceRecord;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.repository.ServiceRecordRepository;
import cz.uhk.fim.servicebookapp.specification.ServiceRecordSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceRecordService {
    private final ServiceRecordRepository serviceRecordRepository;

    public void save(ServiceRecord serviceRecord){
        serviceRecordRepository.save(serviceRecord);
    }

    public Optional<ServiceRecord> getServiceRecordById(Long id){
        return serviceRecordRepository.findById(id);
    }

    public void delete(ServiceRecord serviceRecord){
        serviceRecordRepository.delete(serviceRecord);
    }

    public Page<ServiceRecord> findAllByUser(User user, Long carId, Long operationId, LocalDate startDate, LocalDate endDate, Pageable pageable){
        Specification<ServiceRecord> filters = ServiceRecordSpecification.filterRecords(user.getId(),carId,operationId,startDate,endDate);
        return serviceRecordRepository.findAll(filters, pageable);
    }

    public Integer getUserTotalCosts(User user){
        return serviceRecordRepository.getUserTotalCosts(user);
    }
    public Integer getCarTotalCosts(Car car){
        return serviceRecordRepository.getCarTotalCosts(car);
    }
}
