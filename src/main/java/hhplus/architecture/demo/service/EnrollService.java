package hhplus.architecture.demo.service;

import hhplus.architecture.demo.controller.dto.RequestDTO;
import hhplus.architecture.demo.controller.dto.ResponseDTO;
import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.domain.Lecture;
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
        // lecture가 존재하는지 확인
        List<Lecture> lecture = lectureRepository.findAllByLectureId(lectureId);
        if (lecture.isEmpty())
            throw new ResponseDTO("Lecture does not exist (lectureId : " + lectureId + ")");

        // 이미 enroll 데이터 존재하는지 조회
//        List<Enroll> enrolls = lecture.getEnrolls();
//        List<Enroll> enrollList = enrollRepository.findAllByUserId(userId);
//        if(enrolls.(lectureId))
//            throw new ResponseDTO("Already enrolled");

        // 강의 현재 신청한 N명 초과인지 확인

        return enrollRepository.save(new Enroll(userId, lectureId));
    }

    public Lecture getLecture(Long lectureId)
    {
        return lectureRepository.findByLectureId(lectureId);
    }

    public boolean isEnrollExist(Long userId, Long lectureId)
    {
        List<Enroll> list = enrollRepository.findAllByUserId(userId);

        return list.stream().filter(enroll -> enroll.getLectureId() == lectureId).toList().size() != 0;
    }

    public List<Enroll> getApplication(Long userId)
    {
        return enrollRepository.findAllByUserId(userId);
    }

}
