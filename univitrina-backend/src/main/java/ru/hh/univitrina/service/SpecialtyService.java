package ru.hh.univitrina.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.SpecialtyDao;
import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.dto.SpecialtyShortDto;
import ru.hh.univitrina.dto.TrainingDirectionDto;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.mapper.ProfessionMapper;
import ru.hh.univitrina.mapper.SpecialtyMapper;
import ru.hh.univitrina.mapper.TrainingDirectionMapper;

public class SpecialtyService {

  private final SpecialtyDao specialtyDao;

  @Inject
  public SpecialtyService(SpecialtyDao specialtyDao) {
    this.specialtyDao = specialtyDao;
  }

  @Transactional
  public List<SpecialtyShortDto> getSearchSuggestion(String prefix, Integer limit) {
    return specialtyDao.getSearchSuggestion(prefix, limit).stream()
        .map(SpecialtyMapper::mapToShortDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public List<SpecialtyDto> getFilteredByProfession(Integer page, Integer perPage, Integer professionId) {
    return specialtyDao
        .getFilteredByProfession(page, perPage, professionId)
        .stream()
        .map(SpecialtyMapper::mapToDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public List<SpecialtyDto> getSpecialtyList(String text, Integer trainingDirectionId,
                                             Integer sectionId, Integer professionId, Integer universityId,
                                             Integer page, Integer perPage) {
    return specialtyDao.getSearchByCriteria(text, trainingDirectionId, sectionId, professionId, universityId, page, perPage)
        .stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public Optional<SpecialtyDto> getById(Integer id) {
    return specialtyDao.getById(Specialty.class, id)
        .map(this::convertToDto);
  }

  @Transactional
  public List<SpecialtyShortDto> getDictionary() {
    return specialtyDao.getAll(Specialty.class).stream()
        .map(SpecialtyMapper::mapToShortDto)
        .collect(Collectors.toList());
  }

  private SpecialtyDto convertToDto(Specialty specialty) {
    List<ProfessionDto> professions = specialty.getProfessionSet().stream()
        .sorted(((o1, o2) -> Objects.compare(o1.getName(), o2.getName(), String::compareTo)))
        .map(profession -> {
          ProfessionDto professionDto = ProfessionMapper.mapToDto(profession);
          return professionDto;
        })
        .collect(Collectors.toList());

    TrainingDirection direction = specialty.getTrainingDirection();
    TrainingDirectionDto directionDto = TrainingDirectionMapper.mapToDto(direction);

    SpecialtyDto specialtyDto = SpecialtyMapper.mapToDto(specialty);
    specialtyDto.setProfessionList(professions);
    specialtyDto.setTrainingDirection(directionDto);

    return specialtyDto;
  }

}
