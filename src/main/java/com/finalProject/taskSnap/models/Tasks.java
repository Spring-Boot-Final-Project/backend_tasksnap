package com.finalProject.taskSnap.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String status;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private Boolean allDay;
    private String label;
    private String description;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    @Cascade(CascadeType.ALL)
    private TaskSnapUsers taskSnapUsers;
}
