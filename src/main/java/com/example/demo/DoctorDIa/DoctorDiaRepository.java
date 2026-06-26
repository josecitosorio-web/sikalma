package com.example.demo.DoctorDIa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDiaRepository extends JpaRepository<DoctorDiaEntity, Long> {

    void deleteByDoctorId(Long idDoctor);

    List<DoctorDiaEntity> findByDoctorId(Long idDoctor);
    
}
