package com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.repositories;

import com.thomasmylonas.spring_mvc_jsf_pf_web_app.data_access_layer.entities.StudentAlt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "studentAltRepository")
public interface StudentAltRepository extends JpaRepository<StudentAlt, Long> {
}
