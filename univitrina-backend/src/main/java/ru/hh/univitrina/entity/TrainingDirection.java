package ru.hh.univitrina.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "training_direction")
public class TrainingDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_direction_id")
    private Integer id;

    @Column(name = "training_direction_name")
    private String name;

    @Column(name = "training_direction_code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;


    @OneToMany(mappedBy = "trainingDirection", fetch = FetchType.LAZY)
    private Set<Specialty> specialtySet = new HashSet<>();

    public TrainingDirection() {
    }

    public TrainingDirection(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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
        TrainingDirection that = (TrainingDirection) o;
        return Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(specialtySet, that.specialtySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, specialtySet);
    }
}
