package cz.uhk.fim.servicebookapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carbrandId", nullable = false)
    private CarBrand brand;
    @Column(length = 64, nullable = false)
    private String model;
    @Column(length = 32)
    private String nickname;
    @Column(length = 7)
    private String spz;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
