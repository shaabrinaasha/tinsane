package apap.ti.silogistik2006596705.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public abstract class PermintaanPengirimanMapper {
    public abstract PermintaanPengiriman createPermintaanDTOToPermintaan(CreatePermintaanDTO createPermintaanDTO);
}
