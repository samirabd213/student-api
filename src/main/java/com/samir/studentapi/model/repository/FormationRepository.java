package com.samir.studentapi.model.repository;

import com.samir.studentapi.model.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}
