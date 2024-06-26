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

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCurCapacity() {
        return curCapacity;
    }

    public void setCurCapacity(Integer curCapacity) {
        this.curCapacity = curCapacity;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public LocalDateTime getOpenAt() {
        return openAt;
    }

    public void setOpenAt(LocalDateTime openAt) {
        this.openAt = openAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
