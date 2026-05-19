package training.web.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CurrencyRequestDto {
    @NotBlank(message = "code is required")
    private String code;

    @NotBlank(message = "name is required")
    private String name;
}
