package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.dto.ProfessionShortDto;
import ru.hh.univitrina.entity.Profession;

public class ProfessionMapper {
  public static ProfessionShortDto mapToShortDto(Profession profession) {
    return new ProfessionShortDto(profession.getId(), profession.getName());
  }

  public static ProfessionDto mapToDto(Profession profession) {
    return new ProfessionDto(profession.getId(), profession.getName(), profession.getDescription());
  }
}
