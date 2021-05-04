package ru.hh.univitrina.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.SpecialtyDao;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.mapper.SpecialtyMapper;

public class SpecialtyService {

  private final SpecialtyDao specialtyDao;

  @Inject
  public SpecialtyService(SpecialtyDao specialtyDao) {
    this.specialtyDao = specialtyDao;
  }

  @Transactional
  public List<SpecialtyDto> getSearchSuggestion(String prefix, Integer limit) {
    return specialtyDao.getSearchSuggestion(prefix, limit).stream()
        .map(SpecialtyMapper::mapToDto)
        .collect(Collectors.toList());
  }
}
