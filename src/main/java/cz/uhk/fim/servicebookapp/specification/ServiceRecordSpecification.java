package cz.uhk.fim.servicebookapp.specification;

import cz.uhk.fim.servicebookapp.model.ServiceRecord;
import jakarta.persistence.criteria.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ServiceRecordSpecification {
    public static Specification<ServiceRecord> filterRecords(
            Long userId, Long carId, Long operationId, LocalDate startDate, LocalDate endDate) {
        return (Root<ServiceRecord> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if(userId != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("car").get("user").get("id"), userId));
            }
            if(carId != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("car").get("id"), carId));
            }
            if (operationId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("operation").get("id"), operationId));
            }
            if (startDate != null && endDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("date"), startDate, endDate));
            }
            else if(startDate != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("date"),startDate));
            }
            else if(endDate != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("date"),endDate));
            }
            return predicate;
        };
    }

    public static Specification<ServiceRecord> totalCosts(
            Long userId, Long carId, Long operationId, LocalDate startDate, LocalDate endDate) {
        return (Root<ServiceRecord> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if(userId != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("car").get("user").get("id"), userId));
            }
            if(carId != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("car").get("id"), carId));
            }
            if (operationId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("operation").get("id"), operationId));
            }
            if (startDate != null && endDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("date"), startDate, endDate));
            }
            else if(startDate != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("date"),startDate));
            }
            else if(endDate != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("date"),endDate));
            }

            return predicate;
        };
    }
}
