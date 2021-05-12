package ru.hh.univitrina.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.UniversityDao;
import ru.hh.univitrina.dto.UniversityDto;
import ru.hh.univitrina.mapper.UniversityMapper;

public class UniversityService {

  private final UniversityDao universityDao;

  @Inject
  public UniversityService(UniversityDao universityDao) {
    this.universityDao = universityDao;
  }

  @Transactional
  public List<UniversityDto> getSearchSuggestion(String prefix, Integer limit) {
    return universityDao.getSearchSuggestion(prefix, limit).stream()
        .map(UniversityMapper::mapToDto)
        .collect(Collectors.toList());
  }
}
