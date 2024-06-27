package hhplus.architecture.demo.repository;

import hhplus.architecture.demo.domain.Enroll;
import hhplus.architecture.demo.service.EnrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EnrollRepositoryImpl implements EnrollRepository {

    private final EnrollJpaRepository enrollJpaRepository;

    @Override
    public Enroll save(Enroll enroll) { return enrollJpaRepository.save(enroll);}

    @Override
    public List<Enroll> findAllByUserId(Long userId)
    {
        return enrollJpaRepository.findAllByUserId(userId);
    }

    @Override
    public boolean existsByUserId(Long userId) { return enrollJpaRepository.existsById(userId); };

    @Override
    public
    Enroll findByEnrollId(Long enrollId) {return enrollJpaRepository.findByEnrollId(enrollId); }
}
