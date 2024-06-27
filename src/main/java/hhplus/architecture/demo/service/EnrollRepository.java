package hhplus.architecture.demo.service;

import hhplus.architecture.demo.domain.Enroll;

import java.util.List;

public interface EnrollRepository {

    Enroll save(Enroll enroll);
    List<Enroll> findAllByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
