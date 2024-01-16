package cz.uhk.fim.servicebookapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarAddDto {
    @Min(value = 1, message = "Chybně vybrána značka vozidla")
    @NotNull(message = "Značka vozidla musí být zvolena")
    private Integer carBrand;
    @NotBlank(message = "Model musí být vyplněn")
    @Size(min = 3, max = 64, message = "Model musí mít minimálně 3 a maximálně 64 znaků")
    private String model;
    @Size(min = 3, max = 32, message = "Přezdívka musí mít minimálně 3 a maximálně 32 znaků")
    private String nickname;
    @Size(min = 5, max = 7, message = "Zadejte platnou SPZ ")
    private String spz;
}
