package cz.uhk.fim.servicebookapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotBlank(message = "Uživatelské jméno musí být vyplněno")
    @Size(min = 3, max = 24, message = "Uživatelské jméno může mít minimálně 3 a maximálně 24 znaků")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Uživatelské jméno může obsahovat pouze písmena a číslice")
    private String username;
    @NotBlank(message = "E-Mail musí být vyplněn")
    @Email(message = "E-Mail musí být platný")
    private String email;
    @NotBlank(message = "Heslo musí být vyplněno")
    @Size(min = 8, max = 32, message = "Heslo musí mít minimálně 8 znaků a maximálně 32 znaků")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[/*\\-+#%$=´()§,.~ˇ^˘°˛`˙´˝¨@&}{<>Đ\\[\\]Ł€]).*$", message = "Heslo musí obsahovat malé a velké písmeno, číslo a speciální znak")
    private String password;
    @NotBlank(message = "Prosím potvrďte heslo")
    private String password2;
}
