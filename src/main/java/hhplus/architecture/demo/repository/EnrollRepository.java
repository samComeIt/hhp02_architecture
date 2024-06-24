package hhplus.architecture.demo.repository;

import hhplus.architecture.demo.domain.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository <Enroll, Long>{

    Enroll save(Enroll enroll);
    List<Enroll> findByUserId(Long userId);
}
