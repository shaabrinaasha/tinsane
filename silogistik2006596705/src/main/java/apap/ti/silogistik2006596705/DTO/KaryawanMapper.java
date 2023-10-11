package apap.ti.silogistik2006596705.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2006596705.model.Karyawan;

@Mapper(componentModel = "spring")
public abstract class KaryawanMapper {
    public abstract Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
