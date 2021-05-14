package ru.hh.univitrina.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.SpecialtyDao;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.dto.SpecialtyShortDto;
import ru.hh.univitrina.mapper.SpecialtyMapper;

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
}
