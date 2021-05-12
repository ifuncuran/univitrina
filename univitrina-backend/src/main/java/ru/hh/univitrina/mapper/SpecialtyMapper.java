package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.entity.Specialty;

public class SpecialtyMapper {
  public static SpecialtyDto mapToDto(Specialty specialty) {
    return new SpecialtyDto(specialty.getId(), specialty.getName());
  }
}
