package ru.hh.univitrina.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Integer id;

    @Column(name = "section_name")
    private String name;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private Set<TrainingDirection> trainingDirectionSet = new HashSet<>();

    public Section() {
    }

    public Section(String name) {
        this.name = name;
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

    public Set<TrainingDirection> getTrainingDirectionSet() {
        return trainingDirectionSet;
    }

    public void setTrainingDirectionSet(Set<TrainingDirection> trainingDirectionSet) {
        this.trainingDirectionSet = trainingDirectionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Section section = (Section) o;
        return Objects.equals(name, section.name) && Objects.equals(trainingDirectionSet, section.trainingDirectionSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, trainingDirectionSet);
    }
}
