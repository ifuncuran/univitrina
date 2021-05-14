package ru.hh.univitrina.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import javax.inject.Inject;
import ru.hh.univitrina.client.HhApiClient;
import ru.hh.univitrina.dto.AreaDto;

public class AreaService {
  private static final int REFRESH_PERIOD_IN_HOURS = 24;

  private final HhApiClient hhApiClient;

  @Inject
  public AreaService(HhApiClient hhApiClient) {
    this.hhApiClient = hhApiClient;
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

  public String getAreaNameById(Integer id) {
    return getAreas().get(id).getName();
  }
}
