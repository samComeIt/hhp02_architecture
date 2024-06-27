package hhplus.architecture.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enroll_id")
    private Long enrollId;

    @Column(nullable = false, name = "user_id")
    private Long userId;

//    @Column(nullable = false, name = "lecture_id")
//    private Long lectureId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lecture_id", nullable = false)
    private Lecture lecture;

//    public Enroll(Long userId, Long lectureId)
//    {
//        this.userId = userId;
//        this.lectureId = lectureId;
//        this.createdAt = LocalDateTime.now();
//    }

    public Enroll(Long userId, Lecture lecture)
    {
        this.userId = userId;
        this.lecture = lecture;
        this.createdAt = LocalDateTime.now();
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

//    public Long getLectureId() {
//        return lectureId;
//    }
//
//    public void setLectureId(Long lectureId) {
//        this.lectureId = lectureId;
//    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

//    public boolean isEnrollExist(List<Enroll> enrolls) throws Exception {
//        boolean isExist = enrolls.stream().filter(enroll -> enroll.getLectureId() == this.getLectureId()).toList().size() > 0;
//
//        if (!isExist) {
//            throw new Exception(String.format("fail"));
//        }
//        return true;
//    }
}
