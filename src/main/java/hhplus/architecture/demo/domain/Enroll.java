package hhplus.architecture.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrollId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long lectureId;

    private LocalDateTime createdAt;

    public Enroll(Long userId, Long lectureId, LocalDateTime createdAt)
    {
        this.userId = userId;
        this.lectureId = lectureId;
        this.createdAt = createdAt;
    }

    public Long getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Long enrollId) {
        this.enrollId = enrollId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
