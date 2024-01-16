package cz.uhk.fim.servicebookapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "car_brands")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    private Set<Car> cars;
}
