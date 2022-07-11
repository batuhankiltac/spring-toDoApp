package com.batuhankiltac.toDoApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Must be not null.")
    @NotBlank(message = "Must be not blank.")
    @NotEmpty(message = "Must be not empty.")
    @Size(min = 3, message = "Must be longer than 3 characters.")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Must be not null.")
    @NotBlank(message = "Must be not blank.")
    @NotEmpty(message = "Must be not empty.")
    @Size(min = 10, message = "Must be longer than 10 characters.")
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @NotNull(message = "Must be not null.")
    @NotBlank(message = "Must be not blank.")
    @NotEmpty(message = "Must be not empty.")
    @Column(name = "username", unique = true)
    private String username;
}