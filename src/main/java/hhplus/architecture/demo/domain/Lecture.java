package hhplus.architecture.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lectureId;

    @Column(nullable = false)
    private String title;

    private Integer curCapacity;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(nullable = false)
    private LocalDateTime openAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Lecture(String title, Integer curCapacity, Integer maxCapacity, LocalDateTime openAt)
    {
        this.title = title;
        this.curCapacity = 0;
        this.maxCapacity = maxCapacity;
        this.openAt = openAt;
    }
}
