package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.model.ServiceRecord;
import cz.uhk.fim.servicebookapp.repository.ServiceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
