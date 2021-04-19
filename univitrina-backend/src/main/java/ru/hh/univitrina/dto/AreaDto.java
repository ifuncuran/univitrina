package ru.hh.univitrina.dto;

import java.util.List;

public class AreaDto {

    private Integer id;

    private Integer parentId;

    private String name;

    private List<AreaDto> areas;

    public AreaDto() {
    }

    public AreaDto(Integer id, Integer parentId, String name, List<AreaDto> areas) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.areas = areas;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getParentId() {

        return parentId;
    }

    public void setParentId(Integer parentId) {

        this.parentId = parentId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<AreaDto> getAreas() {

        return areas;
    }

    public void setAreas(List<AreaDto> areas) {

        this.areas = areas;
    }

}
