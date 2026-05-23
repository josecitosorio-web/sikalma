package com.example.demo.Atencion;

import java.util.List;

public interface AtencionDAO {
    void save(AtencionEntity a);
    List<AtencionEntity> findAll();
    AtencionEntity findById(int id);
    void update(AtencionEntity a);
    void delete(int id);
}
