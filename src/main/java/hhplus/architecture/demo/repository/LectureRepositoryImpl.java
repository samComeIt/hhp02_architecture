package hhplus.architecture.demo.repository;

import hhplus.architecture.demo.domain.Lecture;
import hhplus.architecture.demo.service.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public List<Lecture> findAllByLectureId(Long lectureId){
        return lectureJpaRepository.findAllByLectureId(lectureId);
    }

    @Override
    public Lecture save(Lecture lecture)
    {
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public Lecture findByLectureId(Long lectureId)
    {
        return lectureJpaRepository.findByLectureId(lectureId);
    }

}
