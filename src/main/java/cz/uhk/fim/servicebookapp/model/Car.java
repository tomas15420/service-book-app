package cz.uhk.fim.servicebookapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carbrandId", nullable = false)
    @NotNull(message = "Značka vozidla musí být zvolena")
    private CarBrand brand;

    @Column(length = 64, nullable = false)
    @NotBlank(message = "Model musí být vyplněn")
    @Size(min = 3, max = 64, message = "Model musí mít minimálně 3 a maximálně 64 znaků")
    private String model;

    @Column(length = 32)
    @Size(max = 32, message = "Přezdívka může mít maximálně 32 znaků")
    private String nickname;

    @Column(length = 7)
    @Size(min = 5, max = 7, message = "Zadejte platnou SPZ ")
    private String spz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private Set<ServiceRecord> services;
}
