package cz.uhk.fim.servicebookapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotBlank(message = "Uživatelské jméno musí být vyplněno")
    @Size(min = 3, max = 24, message = "Uživatelské jméno může mít minimálně 3 a maximálně 24 znaků")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Uživatelské jméno může obsahovat pouze písmena a číslice")
    private String username;
    @Email(message = "Zadejte platný email")
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[/*\\-+#%$=´()§,.~ˇ^˘°˛`˙´˝¨@&}{<>Đ\\[\\]Ł€]).*$", message = "Heslo musí obsahovat malé a velké písmeno, číslo a speciální znak")
    private String password;
    @NotBlank(message = "Prosím potvrďte heslo")
    private String password2;
}
