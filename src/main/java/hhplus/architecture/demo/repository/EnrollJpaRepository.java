package hhplus.architecture.demo.repository;

import hhplus.architecture.demo.domain.Enroll;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollJpaRepository extends JpaRepository <Enroll, Long>{
    Enroll save(Enroll enroll);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<Enroll> findAllByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
