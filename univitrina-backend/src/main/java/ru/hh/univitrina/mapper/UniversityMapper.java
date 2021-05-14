package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.UniversityDto;
import ru.hh.univitrina.dto.UniversityShortDto;
import ru.hh.univitrina.entity.University;

public class UniversityMapper {
  public static UniversityShortDto mapToShortDto(University university) {
    return new UniversityShortDto(university.getId(), university.getName());
  }

  public static UniversityDto mapToDto(University university) {
    return new UniversityDto(
        university.getId(),
        university.getName(),
        university.getDescription(),
        university.getExtendedDescription());
  }
}
