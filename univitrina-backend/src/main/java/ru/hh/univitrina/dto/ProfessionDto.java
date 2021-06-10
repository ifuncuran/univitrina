package ru.hh.univitrina.dto;

import java.util.Set;

public class ProfessionDto {
  private Integer id;
  private String name;
  private String description;
  private Set<SpecialtyDto> specialties;

  public ProfessionDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public ProfessionDto(Integer id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public ProfessionDto(Integer id, String name, String description, Set<SpecialtyDto> specialties) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.specialties = specialties;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<SpecialtyDto> getSpecialties() {
    return specialties;
  }

  public void setSpecialties(Set<SpecialtyDto> specialties) {
    this.specialties = specialties;
  }

}
