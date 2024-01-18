package cz.uhk.fim.servicebookapp.service;

import cz.uhk.fim.servicebookapp.model.Operation;
import cz.uhk.fim.servicebookapp.model.User;
import cz.uhk.fim.servicebookapp.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;

    public List<Operation> getUserOperations(User user){
        return operationRepository.getOperationsByUser(user);
    }

    public void save(Operation operation){
        operationRepository.save(operation);
    }

    public Optional<Operation> getOperationById(Long id){
        return operationRepository.findById(id);
    }

    public Optional<Operation> getOperationByName(String name){
        return operationRepository.getOperationsByName(name);
    }
}
