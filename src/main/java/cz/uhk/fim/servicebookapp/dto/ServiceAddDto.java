package cz.uhk.fim.servicebookapp.dto;

import cz.uhk.fim.servicebookapp.model.Operation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceAddDto {
    private Long id;
    @Min(value = 0, message = "Minimální hodnota nájezdu musí být 0")
    @NotNull(message = "Stav tachometru musí být vyplněn")
    private Integer mileage;
    @NotNull(message = "Datum musí být vyplněn")
    private LocalDate date;
    @NotNull(message = "Cena musí být  vyplněna")
    @Min(value = 0, message = "Minimální cena musí být 0")
    private Integer cost;
    @Size(max = 512, message = "Maximální délka popisu může být 512 znaků")
    private String description;
    private Long operation;
    @Size(max = 32, message = "Maximální délka úkonu může být 32 znaků")
    private String newOperation;
}
