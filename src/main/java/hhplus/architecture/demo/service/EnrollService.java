package hhplus.architecture.demo.service;

import hhplus.architecture.demo.controller.dto.RequestDTO;
import hhplus.architecture.demo.controller.dto.ResponseDTO;
import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.repository.EnrollRepository;
import hhplus.architecture.demo.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollService {

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private LectureRepository lectureRepository;

    public EnrollService(EnrollRepository enrollRepository, LectureRepository lectureRepository)
    {
        this.enrollRepository = enrollRepository;
        this.lectureRepository = lectureRepository;
    }

    public Enroll apply(Enroll enroll)
    {
        return enrollRepository.save(enroll);
    }

    public List<Enroll> getApplication(Long userId)
    {
        return enrollRepository.findByUserId(userId);
    }

}
