package ru.hh.univitrina.mapper;

import ru.hh.univitrina.dto.TrainingDirectionDto;
import ru.hh.univitrina.entity.TrainingDirection;

public class TrainingDirectionMapper {
  public static TrainingDirectionDto mapToDto(TrainingDirection tDirection) {
    return new TrainingDirectionDto(tDirection.getId(), tDirection.getName(), tDirection.getCode());
  }
}
