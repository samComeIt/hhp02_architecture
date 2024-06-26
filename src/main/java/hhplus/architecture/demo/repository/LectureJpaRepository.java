package hhplus.architecture.demo.repository;

import hhplus.architecture.demo.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAllByLectureId(Long lectureId);

    Lecture save(Lecture lecture);

    Lecture findByLectureId(Long lectureId);

    //Lecture getById(Long lectureId);
}
