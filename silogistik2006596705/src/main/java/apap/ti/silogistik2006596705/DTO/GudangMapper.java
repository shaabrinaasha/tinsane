package apap.ti.silogistik2006596705.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2006596705.model.Gudang;
import apap.ti.silogistik2006596705.DTO.UpdateGudangRequestDTO;

@Mapper(componentModel = "spring")
public abstract class GudangMapper {
    public abstract Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    public abstract UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);

    public abstract Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);
}
