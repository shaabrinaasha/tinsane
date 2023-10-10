package apap.ti.silogistik2006596705.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2006596705.model.Gudang;

@Mapper(componentModel = "spring")
public abstract class GudangMapper {
    public abstract Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
}
