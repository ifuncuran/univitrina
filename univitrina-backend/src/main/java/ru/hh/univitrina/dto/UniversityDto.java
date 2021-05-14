package ru.hh.univitrina.dto;

import java.util.List;

public class UniversityDto {
  private Integer id;
  private String name;
  private String area;
  private String description;
  private String extendedDescription;
  private List<SpecialtyDto> specialtyList;

  public UniversityDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public UniversityDto(Integer id, String name, String description, String extendedDescription) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.extendedDescription = extendedDescription;
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

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getExtendedDescription() {
    return extendedDescription;
  }

  public void setExtendedDescription(String extendedDescription) {
    this.extendedDescription = extendedDescription;
  }

  public List<SpecialtyDto> getSpecialtyList() {
    return specialtyList;
  }

  public void setSpecialtyList(List<SpecialtyDto> specialtyList) {
    this.specialtyList = specialtyList;
  }
}
