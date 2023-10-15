package apap.ti.silogistik2006596705.DTO;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2006596705.model.Karyawan;
import apap.ti.silogistik2006596705.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanDTO {
    @NotBlank
    String namaPenerima;

    @NotBlank
    String alamatPenerima;

    @NotBlank
    Date tanggalPengiriman;

    int jenisLayanan;

    @Positive
    int biayaPengiriman;

    @NotBlank
    Karyawan karyawanId;

    List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
