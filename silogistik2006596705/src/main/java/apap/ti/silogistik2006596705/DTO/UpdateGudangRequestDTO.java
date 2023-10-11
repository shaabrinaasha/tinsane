package apap.ti.silogistik2006596705.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import apap.ti.silogistik2006596705.model.GudangBarang;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO {
    private Long id;

    private List<GudangBarang> listGudangBarang;
}
