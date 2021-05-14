package ru.hh.univitrina.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialty_id")
    private Integer id;

    @Column(name = "specialty_name")
    private String name;

    @Column(name = "specialty_code")
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_direction_id", nullable = false)
    private TrainingDirection trainingDirection;

    @ManyToMany(mappedBy = "specialtySet")
    protected Set<Profession> professionSet = new HashSet<>();
    @ManyToMany(mappedBy = "specialtySet")
    protected Set<University> universitySet = new HashSet<>();

    public Specialty() {
    }

    public Specialty(String name, String code) {
        this.name = name;
        this.code = code;
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

    public TrainingDirection getTrainingDirection() {
        return trainingDirection;
    }

    public void setTrainingDirection(TrainingDirection trainingDirection) {
        this.trainingDirection = trainingDirection;
    }

    public Set<Profession> getProfessionSet() {
        return professionSet;
    }

    public void setProfessionSet(Set<Profession> professionSet) {
        this.professionSet = professionSet;
    }

    public Set<University> getUniversitySet() {
        return universitySet;
    }

    public void setUniversitySet(Set<University> universitySet) {
        this.universitySet = universitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Specialty specialty = (Specialty) o;
        return Objects.equals(name, specialty.name) && Objects.equals(code, specialty.code) &&
                Objects.equals(professionSet, specialty.professionSet) && Objects.equals(universitySet, specialty.universitySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, universitySet);
    }
}
