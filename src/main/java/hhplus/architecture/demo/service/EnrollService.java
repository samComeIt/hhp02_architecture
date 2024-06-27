package hhplus.architecture.demo.service;

import hhplus.architecture.demo.controller.dto.RequestDTO;
import hhplus.architecture.demo.controller.dto.ResponseDTO;
import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class EnrollService {

    @Autowired
    private final EnrollRepository enrollRepository;

    @Autowired
    private final LectureRepository lectureRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EnrollService(EnrollRepository enrollRepository, LectureRepository lectureRepository)
    {
        this.enrollRepository = enrollRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public Lecture create(Lecture lecture)
    {
        return lectureRepository.save(lecture);
    }

    @Transactional
    public Enroll apply(Long userId, Long lectureId)
    {
        Lecture lecture = entityManager.find(Lecture.class, lectureId, LockModeType.PESSIMISTIC_WRITE);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        // lecture가 존재하는지 확인
//        List<Lecture> lecture = lectureRepository.findAllByLectureId(lectureId);
//        if (lecture.isEmpty())
//            throw new ResponseDTO("Lecture does not exist (lectureId : " + lectureId + ")");
//
//        Lecture lecture02 = lectureRepository.getById(lectureId);
//        // 강의 현재 신청한 N명 초과인지 확인
//        lecture02.increaseEnrollCount();

        lecture.increaseEnrollCount();

        // 이미 enroll 데이터 존재하는지 조회
        boolean doesExist = this.doesEnrollExistByUser(userId);
        if (doesExist) throw new ResponseDTO("Already enrolled");

        // 강의 현재 수강생수 업데이트
        lecture.setCurCapacity(lecture.getCurCapacity());
        lectureRepository.save(lecture);

        // 수강신청 등록
        return enrollRepository.save(new Enroll(userId, lecture));
    }

    public Lecture getLecture(Long lectureId)
    {
        return lectureRepository.getById(lectureId);
    }

    public boolean isEnrollExist(Long userId, Long lectureId)
    {
        List<Enroll> list = lectureRepository.findAllEnrollByLectureId(lectureId);

        return list.stream().filter(enroll -> enroll.getUserId() == userId).toList().size() != 0;
    }

    public boolean doesEnrollExistByUser(Long userId)
    {
        return enrollRepository.existsByUserId(userId);
    }

    public List<Enroll> getApplication(Long userId)
    {
        return enrollRepository.findAllByUserId(userId);
    }

}
