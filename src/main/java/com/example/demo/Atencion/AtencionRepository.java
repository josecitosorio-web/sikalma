package com.example.demo.Atencion;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AtencionRepository extends JpaRepository<AtencionEntity, Long> {

}
