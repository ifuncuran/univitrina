package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.entity.Profession;

public class ProfessionMapper {
  public static ProfessionDto mapToDto(Profession profession) {
    return new ProfessionDto(profession.getId(), profession.getName());
  }
}
