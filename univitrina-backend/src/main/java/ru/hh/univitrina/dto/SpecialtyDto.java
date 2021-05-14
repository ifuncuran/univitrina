package ru.hh.univitrina.dto;

public class SpecialtyDto {
  private Integer id;
  private String name;
  private String code;
  private String description;
  private TrainingDirectionDto trainingDirection;

  public SpecialtyDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public SpecialtyDto(Integer id, String name, String code, String description) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TrainingDirectionDto getTrainingDirection() {
    return trainingDirection;
  }

  public void setTrainingDirection(TrainingDirectionDto trainingDirection) {
    this.trainingDirection = trainingDirection;
  }
}
