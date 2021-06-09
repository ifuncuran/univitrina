package ru.hh.univitrina.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hh.univitrina.dao.ProfessionDao;
import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.dto.ProfessionShortDto;
import ru.hh.univitrina.mapper.ProfessionMapper;

public class ProfessionService {

  private final ProfessionDao professionDao;

  @Inject
  public ProfessionService(ProfessionDao professionDao) {
    this.professionDao = professionDao;
  }

  @Transactional
  public ProfessionDto getProfessionById(Integer professionId) {

    return ProfessionMapper.mapToDto(professionDao.getProfessionById(professionId));
  }

  @Transactional
  public List<ProfessionShortDto> getSearchSuggestion(String prefix, Integer limit) {
    return professionDao.getSearchSuggestion(prefix, limit).stream()
        .map(ProfessionMapper::mapToShortDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public List<ProfessionDto> getFilteredBySpecialty(Integer page, Integer perPage, Integer specialtyId) {
    return professionDao
            .getFilteredBySpecialty(page, perPage, specialtyId)
            .stream()
            .map(ProfessionMapper::mapToDto)
            .collect(Collectors.toList());
  }

  @Transactional
  public List<ProfessionDto> getFilteredByProfessionNameAndSpecialtyId(String professionName, Integer specialtyId) {

    return professionDao
            .getFilteredByProfessionNameAndSpecialtyId(professionName, specialtyId)
            .stream()
            .map(ProfessionMapper::mapToDto)
            .collect(Collectors.toList());
  }

  @Transactional
  public List<ProfessionDto> getFilteredByProfessionName(String professionName) {

    return professionDao
            .getFilteredByProfessionName(professionName)
            .stream()
            .map(ProfessionMapper::mapToDto)
            .collect(Collectors.toList());
  }

  @Transactional
  public List<ProfessionDto> getFilteredBySpecialtyId(Integer specialtyId) {

    return professionDao
            .getFilteredBySpecialtyId(specialtyId)
            .stream()
            .map(ProfessionMapper::mapToDto)
            .collect(Collectors.toList());
  }

  @Transactional
  public List<ProfessionDto> getAllProfessions() {

    return professionDao
            .getAll()
            .stream()
            .map(ProfessionMapper::mapToDto)
            .collect(Collectors.toList());
  }

}
