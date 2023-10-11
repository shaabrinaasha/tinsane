package apap.ti.silogistik2006596705.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2006596705.model.Barang;

@Mapper(componentModel = "spring")
public abstract class BarangMapper {
    public abstract Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
}
