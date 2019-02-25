package com.lambdaschool.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    private String username;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @JsonIgnore
    private Set<Todo> todos;

    public User()
    {
    }

}
