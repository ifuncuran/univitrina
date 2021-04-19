package ru.hh.univitrina.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;
import java.util.Set;

@Entity
public class Profession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "profession_id")
  private Integer id;

  @Column(name = "profession_name")
  private String name;

  @Column(name = "hhapi_id")
  private String hhapiId;

  @ManyToMany()
  @JoinTable(
      name = "specialty_profession",
      joinColumns = @JoinColumn(name = "profession_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id")
  )
  private Set<Specialty> specialtySet;

  public Profession() {
  }

  public Profession(String name, String hhapiId) {
    this.name = name;
    this.hhapiId = hhapiId;
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

  public String getHhapiId() {
    return hhapiId;
  }

  public void setHhapiId(String hhapiId) {
    this.hhapiId = hhapiId;
  }

  public Set<Specialty> getSpecialtySet() {
    return specialtySet;
  }

  public void setSpecialtySet(Set<Specialty> specialtySet) {
    this.specialtySet = specialtySet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profession that = (Profession) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(hhapiId, that.hhapiId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, hhapiId);
  }

 }
