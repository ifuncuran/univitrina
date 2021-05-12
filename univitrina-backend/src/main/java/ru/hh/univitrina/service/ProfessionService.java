package ru.hh.univitrina.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.ProfessionDao;
import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.mapper.ProfessionMapper;

public class ProfessionService {

  private final ProfessionDao professionDao;

  @Inject
  public ProfessionService(ProfessionDao professionDao) {
    this.professionDao = professionDao;
  }

  @Transactional
  public List<ProfessionDto> getSearchSuggestion(String prefix, Integer limit) {
    return professionDao.getSearchSuggestion(prefix, limit).stream()
        .map(ProfessionMapper::mapToDto)
        .collect(Collectors.toList());
  }
}
