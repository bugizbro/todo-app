package com.gohoshisohan.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    String id;
    @Column(name="name")
    String name;
    @Column(name="created_at")
    LocalDate createdAt = LocalDate.now();
    @Column(name="finished_at")
    LocalDate finishedAt;
}
