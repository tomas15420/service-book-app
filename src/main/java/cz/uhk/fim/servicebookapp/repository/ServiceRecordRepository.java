package cz.uhk.fim.servicebookapp.repository;

import cz.uhk.fim.servicebookapp.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
}
