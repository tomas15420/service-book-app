package cz.uhk.fim.servicebookapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "service-records")
@Data
public class ServiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mileage;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer cost;

    @Column(length = 512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operationId", nullable = false)
    private Operation operation;
}
