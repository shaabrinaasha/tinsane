package apap.ti.silogistik2006596705.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2006596705.model.Barang;
import apap.ti.silogistik2006596705.DTO.UpdateBarangRequestDTO;

@Mapper(componentModel = "spring")
public abstract class BarangMapper {
    // map dto to barang
    public abstract Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    // map barang to dto
    public abstract UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    // map dto to barang
    public abstract Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
}
