package com.pismo.exam.domain;


import jakarta.persistence.*;


@Entity
@Table(name = "Operation_Types")
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String description;

    public OperationType(Long id, String description){
        this.id = id;
        this.description = description;
    }

    public OperationType(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
