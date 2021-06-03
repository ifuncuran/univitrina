package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.dto.SpecialtyShortDto;
import ru.hh.univitrina.entity.Specialty;

public class SpecialtyMapper {
  public static SpecialtyShortDto mapToShortDto(Specialty specialty) {
    return new SpecialtyShortDto(specialty.getId(), specialty.getName());
  }

  public static SpecialtyDto mapToDto(Specialty specialty) {
    return new SpecialtyDto(specialty.getId(), specialty.getName(), specialty.getCode(), specialty.getDescription());
  }
}
