package cz.uhk.fim.servicebookapp.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operations")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 32)
    private String name;
}
