package ru.hh.univitrina.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.UniversityDao;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.dto.TrainingDirectionDto;
import ru.hh.univitrina.dto.UniversityDto;
import ru.hh.univitrina.dto.UniversityShortDto;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;
import ru.hh.univitrina.mapper.SpecialtyMapper;
import ru.hh.univitrina.mapper.TrainingDirectionMapper;
import ru.hh.univitrina.mapper.UniversityMapper;

public class UniversityService {

  private final UniversityDao universityDao;
  private final AreaService areaService;

  @Inject
  public UniversityService(UniversityDao universityDao, AreaService areaService) {
    this.universityDao = universityDao;
    this.areaService = areaService;
  }

  @Transactional
  public List<UniversityShortDto> getSearchSuggestion(String prefix, Integer limit) {
    return universityDao.getSearchSuggestion(prefix, limit).stream()
        .map(UniversityMapper::mapToShortDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public Optional<UniversityDto> getById(Integer id) {
    return universityDao.getById(University.class, id)
        .map(this::convertToDto);
  }

  private UniversityDto convertToDto(University university) {
    List<SpecialtyDto> specialties = university.getSpecialtySet().stream()
        .sorted((o1, o2) -> Objects.compare(o1.getCode(), o2.getCode(), String::compareTo))
        .map(specialty -> {
          TrainingDirection direction = specialty.getTrainingDirection();
          TrainingDirectionDto tDirectionDto = TrainingDirectionMapper.mapToDto(direction);
          SpecialtyDto specialtyDto = SpecialtyMapper.mapToDto(specialty);
          specialtyDto.setTrainingDirection(tDirectionDto);
          return specialtyDto;
        })
        .collect(Collectors.toList());

    UniversityDto universityDto = UniversityMapper.mapToDto(university);
    universityDto.setSpecialtyList(specialties);
    universityDto.setArea(areaService.getAreaNameById(university.getAreaId()));

    return universityDto;
  }
}
