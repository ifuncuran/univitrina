package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.dto.ProfessionShortDto;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.entity.Profession;

import java.util.Set;
import java.util.stream.Collectors;

public class ProfessionMapper {
  public static ProfessionShortDto mapToShortDto(Profession profession) {
    return new ProfessionShortDto(profession.getId(), profession.getName());
  }

  public static ProfessionDto mapToDto(Profession profession) {
    Set<SpecialtyDto> specialties= profession
            .getSpecialtySet()
            .stream()
            .map(SpecialtyMapper::mapToDto)
            .collect(Collectors.toSet());

    return new ProfessionDto(profession.getId(), profession.getName(), profession.getDescription(), specialties);
  }

}
