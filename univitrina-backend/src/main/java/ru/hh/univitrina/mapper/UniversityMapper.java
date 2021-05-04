package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.UniversityDto;
import ru.hh.univitrina.entity.University;

public class UniversityMapper {
  public static UniversityDto mapToDto(University university) {
    return new UniversityDto(university.getId(), university.getName());
  }
}
