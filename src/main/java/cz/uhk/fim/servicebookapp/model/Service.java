package cz.uhk.fim.servicebookapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service-records")
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mileage;

    private ZonedDateTime date;

    private Integer cost;

    @Column(length = 512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "service_operations",
            joinColumns = @JoinColumn(name = "serviceId", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "operationId", nullable = false)
    )
    private Set<Operation> operations;
}
