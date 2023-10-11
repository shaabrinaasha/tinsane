package apap.ti.silogistik2006596705.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String sku;

    private int tipeBarang;

    @NotBlank
    private String merk;

    @Positive
    private Long hargaBarang;
}
