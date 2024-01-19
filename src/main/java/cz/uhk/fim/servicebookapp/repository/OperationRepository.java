package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.Operation;
import cz.uhk.fim.servicebookapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> getOperationsByUser(User user);
    Optional<Operation> getOperationsByUserAndName(User User, String name);
}
