package apap.ti.silogistik2006596705.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    @NotBlank
    private String nama;

    @NotBlank
    private String alamatGudang;
}
