package ru.hh.univitrina.dao;

import java.util.Set;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;

public class CreateObjectsUtil {

  static Specialty createSpecialty(Integer id, String name, String code) {
    Specialty specialty = new Specialty(name, code);
    specialty.setId(id);
    return specialty;
  }

  static Specialty createSpecialty(String name, String code, TrainingDirection trainingDirection) {
    Specialty specialty = new Specialty(name, code);
    specialty.setTrainingDirection(trainingDirection);
    return specialty;
  }

  static TrainingDirection createTrainingDirection(String name, String code, Section section) {
    TrainingDirection tDirection = new TrainingDirection(name, code);
    tDirection.setSection(section);
    return tDirection;
  }

  static University createUniversity(Integer id, String name, Integer areaId) {
    University university = new University(name, areaId);
    university.setId(id);
    return university;
  }

  static University createUniversity(String name, Integer areaId, Set<Specialty> specialtySet) {
    University university = new University(name, areaId);
    university.setSpecialtySet(specialtySet);
    return university;
  }

  static Profession createProfession(String name, String hhapiId) {
    Profession profession = new Profession();
    profession.setName(name);
    profession.setHhapiId(hhapiId);
    return profession;
  }
}
