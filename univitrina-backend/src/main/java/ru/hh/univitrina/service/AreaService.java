package ru.hh.univitrina.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;

import ru.hh.univitrina.client.HhApiClient;
import ru.hh.univitrina.dao.UniversityDao;
import ru.hh.univitrina.dto.AreaDto;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


public class AreaService {
  private static final int REFRESH_PERIOD_IN_HOURS = 24;

  private final HhApiClient hhApiClient;
  private final UniversityDao universityDao;

  @Inject
  public AreaService(HhApiClient hhApiClient, UniversityDao universityDao) {
    this.hhApiClient = hhApiClient;
    this.universityDao = universityDao;
  }

  private Map<Integer, AreaDto> areas;
  private LocalDateTime updateTime;

  private Map<Integer, AreaDto> getAreas() {
    if (areas == null || Duration.between(updateTime, LocalDateTime.now()).toHours() > REFRESH_PERIOD_IN_HOURS) {
      synchronized (this) {
        if (areas == null || Duration.between(updateTime, LocalDateTime.now()).toHours() > REFRESH_PERIOD_IN_HOURS) {
          Map<Integer, AreaDto> tmpAreas = new HashMap<>();
          Queue<AreaDto> queue = new ArrayDeque<>(Set.of(hhApiClient.getRussiaArea()));
          while (!queue.isEmpty()) {
            AreaDto dto = queue.poll();
            tmpAreas.put(dto.getId(), dto);
            queue.addAll(dto.getAreas());
          }
          updateTime = LocalDateTime.now();
          areas = tmpAreas;
        }
      }
    }
    return areas;
  }
  
  private Map<Integer, AreaDto> getAreasWithUniversity(){
    List<Integer> univAreaIds = universityDao.getAreaId();
    return getAreas().entrySet().stream()
        .filter(entry -> univAreaIds.contains(entry.getKey()))
        .collect(toMap(Entry::getKey, Entry::getValue));
  }

  private List<AreaDto> getCities(Map<Integer, AreaDto> areas) {
    return areas.values().stream()
        .filter(areaDto -> areaDto.getAreas().isEmpty())
        .collect(toList());
  }
  
  @Transactional
  public String getAreaNameById(Integer id) {
    return getAreas().get(id).getName();
  }

  @Transactional
  public List<AreaDto> getAllCities(){
    return getCities(getAreas());
  }
  
  @Transactional
  public List<AreaDto> getCitiesWithUniversity(){
    return getCities(getAreasWithUniversity());
  }
}
