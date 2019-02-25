package com.lambdaschool.javatodo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Todo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private String datestarted;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public Todo()
    {
    }

}
