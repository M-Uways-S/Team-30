package com.sheryians.major.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.Fetch;

import java.util.List;

@Data
@Entity
@Table (name = "roles")
@JsonIgnoreProperties({"users"})
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column (nullable = false, unique = true)
    @NotEmpty
    private String name;


    @ManyToMany(fetch=FetchType.LAZY ,mappedBy = "roles")
    private List<User> users;
}
