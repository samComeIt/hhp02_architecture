package hhplus.architecture.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hhplus.architecture.demo.controller.dto.ResponseDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    @Column(nullable = false)
    private String title;

    @Column(name = "cur_capacity")
    private Integer curCapacity;

    @Column(nullable = false, name = "max_capacity")
    private Integer maxCapacity;

    @Column(nullable = false, name = "open_at")
    private LocalDateTime openAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Enroll> enrolls = new ArrayList<>();

    public Lecture(String title, Integer curCapacity, Integer maxCapacity, LocalDateTime openAt)
    {
        this.title = title;
        this.curCapacity = curCapacity;
        this.maxCapacity = maxCapacity;
        this.openAt = openAt;
        this.createdAt = LocalDateTime.now();
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

    public List<Enroll> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(List<Enroll> enrolls) {
        this.enrolls = enrolls;
    }

    public boolean isExist()
    {
        if (this.lectureId == null) return false;

        return true;
    }

    public void increaseEnrollCount() {
        if (this.maxCapacity == this.curCapacity) {
            throw new ResponseDTO("over");
        }
        this.curCapacity++;
    }

    public boolean hasUserEnrolled(Long userId)
    {
        for(Enroll enroll: enrolls)
        {
            if (enroll.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

}
