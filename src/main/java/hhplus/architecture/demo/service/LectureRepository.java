package hhplus.architecture.demo.service;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;

import java.util.List;

public interface LectureRepository {

    Lecture save(Lecture lecture);
    List<Lecture> findAllByLectureId(Long userId);

    Lecture findByLectureId(Long lectureId);

}