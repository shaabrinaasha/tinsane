package apap.ti.silogistik2006596705.DTO;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    @NotBlank
    private String nama;

    @NotBlank
    private int jenisKelamin;

    @NotBlank
    private Date tanggalLahir;
}
